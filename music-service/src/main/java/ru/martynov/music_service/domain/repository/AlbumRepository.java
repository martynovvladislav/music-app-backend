package ru.martynov.music_service.domain.repository;

import org.apache.commons.lang3.tuple.Pair;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;

import java.util.List;
import java.util.Optional;

public interface AlbumRepository {
    Album save(Album album);
    Pair<Album, List<Track>> saveAlbumWithTracks(Album album, List<Track> tracks);

    List<Album> findAlbumsByArtist(Artist artist);

    Optional<Album> findById(Long id);
}
