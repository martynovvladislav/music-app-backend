package ru.martynov.search_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.martynov.search_service.document.Album;
import ru.martynov.search_service.dto.AlbumSearchPreview;
import ru.martynov.search_service.kafka.event.AlbumCreatedEvent;
import ru.martynov.search_service.repository.AlbumRepository;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final TrackService trackService;

    @Transactional
    public void save(final AlbumCreatedEvent albumCreatedEvent) {
        albumRepository.save(mapToDomainEntity(albumCreatedEvent));
        trackService.save(albumCreatedEvent.tracks);
    }

    public List<AlbumSearchPreview> searchByTitle(final String prefix) {
        return albumRepository.findByTitlePrefix(prefix).stream()
                .map(this::mapToPreview)
                .toList();
    }

    private Album mapToDomainEntity(AlbumCreatedEvent albumCreatedEvent) {
        return Album.builder()
                .albumId(albumCreatedEvent.albumId)
                .title(albumCreatedEvent.title)
                .artistId(albumCreatedEvent.artistId)
                .artistName(albumCreatedEvent.artistName)
                .releaseDate(albumCreatedEvent.releaseDate)
                .coverUrl(albumCreatedEvent.coverUrl)
                .build();
    }

    private AlbumSearchPreview mapToPreview(Album album) {
        return AlbumSearchPreview.builder()
                .albumId(album.getAlbumId())
                .title(album.getTitle())
                .artistId(album.getArtistId())
                .artistName(album.getArtistName())
                .releaseDate(album.getReleaseDate())
                .coverUrl(album.getCoverUrl())
                .build();
    }
}
