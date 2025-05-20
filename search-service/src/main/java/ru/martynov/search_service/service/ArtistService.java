package ru.martynov.search_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.martynov.search_service.document.Album;
import ru.martynov.search_service.document.Artist;
import ru.martynov.search_service.dto.ArtistSearchPreview;
import ru.martynov.search_service.kafka.event.ArtistCreatedEvent;
import ru.martynov.search_service.repository.ArtistRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    public void save(final ArtistCreatedEvent artistCreatedEvent) {
        artistRepository.save(mapToDomainEntity(artistCreatedEvent));
    }

    public List<ArtistSearchPreview> searchByName(final String prefix) {
        return artistRepository.findByNamePrefix(prefix).stream()
                .map(this::mapToPreview)
                .toList();
    }

    private Artist mapToDomainEntity(final ArtistCreatedEvent artistCreatedEvent) {
        return Artist.builder()
                .artistId(artistCreatedEvent.artistId)
                .name(artistCreatedEvent.name)
                .photoUrl(artistCreatedEvent.photoUrl)
                .build();
    }

    private ArtistSearchPreview mapToPreview(final Artist artist) {
        return ArtistSearchPreview.builder()
                .artistId(artist.getArtistId())
                .name(artist.getName())
                .photoUrl(artist.getPhotoUrl())
                .build();
    }
}
