package ru.martynov.music_service.infrastructure.persistance.repository.track;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.martynov.music_service.infrastructure.persistance.model.TrackEntity;

import java.util.List;

public interface SpringDataTrackRepository extends JpaRepository<TrackEntity, Long> {
    List<TrackEntity> findAllByAlbumId(Long albumId);
}
