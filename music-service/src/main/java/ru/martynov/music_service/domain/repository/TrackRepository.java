package ru.martynov.music_service.domain.repository;

import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Track;

import java.util.List;

public interface TrackRepository {
    List<Track> findAllByAlbum(Album album);

    List<Track> findAllByIds(List<Long> ids);
}
