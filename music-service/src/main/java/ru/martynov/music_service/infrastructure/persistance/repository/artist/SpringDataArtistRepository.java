package ru.martynov.music_service.infrastructure.persistance.repository.artist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.martynov.music_service.infrastructure.persistance.model.ArtistEntity;

public interface SpringDataArtistRepository extends JpaRepository<ArtistEntity, Long> {
}
