package ru.martynov.music_service.domain.repository;

import ru.martynov.music_service.domain.entity.Artist;

import java.util.Optional;

public interface ArtistRepository {
    Artist save(Artist artist);
    Optional<Artist> findById(Long id);
}
