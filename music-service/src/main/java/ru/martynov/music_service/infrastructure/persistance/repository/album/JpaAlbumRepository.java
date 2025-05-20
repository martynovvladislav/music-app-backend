package ru.martynov.music_service.infrastructure.persistance.repository.album;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.repository.AlbumRepository;
import ru.martynov.music_service.infrastructure.persistance.model.AlbumEntity;
import ru.martynov.music_service.infrastructure.persistance.model.ArtistEntity;
import ru.martynov.music_service.infrastructure.persistance.model.TrackEntity;
import ru.martynov.music_service.infrastructure.persistance.repository.artist.SpringDataArtistRepository;
import ru.martynov.music_service.infrastructure.persistance.repository.track.SpringDataTrackRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaAlbumRepository implements AlbumRepository {
    private final SpringDataAlbumRepository albumRepository;
    private final SpringDataTrackRepository trackRepository;
    private final SpringDataArtistRepository artistRepository;
    @Override
    public Album save(Album album) {
        AlbumEntity albumEntity = AlbumEntity.create(album);
        return albumRepository.save(albumEntity).toDomainEntity();
    }

    @Override
    public Pair<Album, List<Track>> saveAlbumWithTracks(Album album, List<Track> tracks) {
        AlbumEntity albumEntity = saveAndGetAlbumEntity(album);
        ArtistEntity artistEntity = artistRepository.findById(album.getArtist().getArtistId()).orElseThrow();

        List<TrackEntity> trackEntities = tracks.stream()
            .map(track -> TrackEntity.fromDomainEntity(track, albumEntity, artistEntity))
            .toList();

        List<TrackEntity> resultTracks = trackRepository.saveAll(trackEntities);

        return Pair.of(albumEntity.toDomainEntity(), resultTracks.stream().map(TrackEntity::toDomainEntity).toList());
    }

    @Override
    public List<Album> findAlbumsByArtist(Artist artist) {
        return albumRepository.findAllByArtistId(artist.getArtistId()).stream()
                .map(AlbumEntity::toDomainEntity)
                .toList();
    }

    @Override
    public Optional<Album> findById(Long id) {
        return albumRepository.findById(id).map(AlbumEntity::toDomainEntity);
    }

    private AlbumEntity saveAndGetAlbumEntity(Album album) {
        AlbumEntity albumEntity = AlbumEntity.create(album);
        return albumRepository.save(albumEntity);
    }
}
