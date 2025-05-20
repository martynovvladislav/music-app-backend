package ru.martynov.music_service.application.command.addalbum;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.music_service.application.command.CommandHandler;
import ru.martynov.music_service.application.event.MusicEventPublisher;
import ru.martynov.music_service.application.event.MusicEventPublisherAdapter;
import ru.martynov.music_service.application.exception.BusinessException;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.repository.AlbumRepository;
import ru.martynov.music_service.domain.repository.ArtistRepository;
import ru.martynov.music_service.domain.repository.TrackRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AddAlbumHandler implements CommandHandler<AddAlbum> {
    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final MusicEventPublisher musicEventPublisher;

    @Override
    @Transactional
    public Result<Void> handle(AddAlbum command) {
        Artist artist = artistRepository.findById(command.getArtistId())
                .orElseThrow(() -> new BusinessException("Artist not found"));

        Album album = Album.create(command.getTitle(), artist, command.getReleaseDate(), command.getCoverUrl());

        List<Track> tracks = command.getTracks().stream()
                .map(trackInfo -> Track.create(
                        trackInfo.getTitle(),
                        artist,
                        album,
                        trackInfo.getGenre(),
                        trackInfo.getAudioUrl(),
                        trackInfo.getCoverUrl(),
                        command.getReleaseDate()
                ))
                .toList();

        Pair<Album, List<Track>> result = albumRepository.saveAlbumWithTracks(album, tracks);

        musicEventPublisher.publishAlbumCreationEvent(result.getLeft(), result.getRight());

        return Result.success();
    }

    @Override
    public Class<AddAlbum> getCommandType() {
        return AddAlbum.class;
    }
}
