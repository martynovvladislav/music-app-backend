package ru.martynov.music_service.infrastructure.persistance.repository.album;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.martynov.music_service.infrastructure.persistance.model.AlbumEntity;

import java.util.List;

public interface SpringDataAlbumRepository extends JpaRepository<AlbumEntity, Long> {
    List<AlbumEntity> findAllByArtistId(Long artistId);
}
