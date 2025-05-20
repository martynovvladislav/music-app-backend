package ru.martynov.music_service.infrastructure.persistance.repository.track;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.repository.AlbumRepository;
import ru.martynov.music_service.domain.repository.TrackRepository;
import ru.martynov.music_service.infrastructure.persistance.model.TrackEntity;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaTrackRepository implements TrackRepository {
    private final SpringDataTrackRepository trackRepository;

    @Override
    public List<Track> findAllByAlbum(Album album) {
        return trackRepository.findAllByAlbumId(album.getAlbumId()).stream()
                .map(TrackEntity::toDomainEntity)
                .toList();
    }

    @Override
    public List<Track> findAllByIds(List<Long> ids) {
        return trackRepository.findAllById(ids).stream()
                .map(TrackEntity::toDomainEntity)
                .toList();
    }
}
