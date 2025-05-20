package ru.martynov.search_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.martynov.search_service.document.Track;
import ru.martynov.search_service.dto.TrackSearchSummary;
import ru.martynov.search_service.kafka.event.TrackCreatedEvent;
import ru.martynov.search_service.repository.TrackRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackService {
    private final TrackRepository trackRepository;

    public void save(final List<TrackCreatedEvent> trackCreatedEvents) {
        trackRepository.saveAll(mapToDomainEntities(trackCreatedEvents));
    }

    public List<TrackSearchSummary> searchByTitle(String prefix) {
        return trackRepository.findByTitlePrefix(prefix).stream()
                .map(this::mapToSummary)
                .toList();
    }

    private List<Track> mapToDomainEntities(final List<TrackCreatedEvent> trackCreatedEvents) {
        return trackCreatedEvents.stream().map(this::mapToDomainEntity).toList();
    }

    private Track mapToDomainEntity(final TrackCreatedEvent trackCreatedEvent) {
        return Track.builder()
                .trackId(trackCreatedEvent.trackId)
                .title(trackCreatedEvent.title)
                .genre(trackCreatedEvent.genre)
                .artistId(trackCreatedEvent.artistId)
                .artistName(trackCreatedEvent.artistName)
                .albumId(trackCreatedEvent.albumId)
                .albumTitle(trackCreatedEvent.albumTitle)
                .audioUrl(trackCreatedEvent.audioUrl)
                .coverUrl(trackCreatedEvent.coverUrl)
                .releaseDate(trackCreatedEvent.releaseDate)
                .build();
    }



    private TrackSearchSummary mapToSummary(final Track track) {
        return TrackSearchSummary.builder()
                .trackId(track.getTrackId())
                .title(track.getTitle())
                .genre(track.getGenre())
                .artistId(track.getArtistId())
                .artistName(track.getArtistName())
                .albumId(track.getAlbumId())
                .albumTitle(track.getAlbumTitle())
                .audioUrl(track.getAudioUrl())
                .coverUrl(track.getCoverUrl())
                .releaseDate(track.getReleaseDate())
                .build();
    }
}
