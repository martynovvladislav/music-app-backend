package ru.martynov.music_service.application.event;

import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;

import java.util.List;

public interface MusicEventPublisher {
    void publishArtistCreationEvent(Artist artist);

    void publishAlbumCreationEvent(Album album, List<Track> tracks);
}
