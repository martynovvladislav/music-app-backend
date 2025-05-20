package ru.martynov.search_service.kafka.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.martynov.search_service.kafka.event.AlbumCreatedEvent;
import ru.martynov.search_service.kafka.event.ArtistCreatedEvent;
import ru.martynov.search_service.service.AlbumService;
import ru.martynov.search_service.service.ArtistService;

@Service
@Slf4j
@RequiredArgsConstructor
@KafkaListener(
        topics = "track-events",
        groupId = "search-service",
        properties = {
                "spring.json.value.default.type:java.lang.String",
                "spring.json.type.mapping: " +
                    "ALBUM_CREATED:ru.martynov.search_service.kafka.event.AlbumCreatedEvent, " +
                    "ARTIST_CREATED:ru.martynov.search_service.kafka.event.ArtistCreatedEvent"
        }
)
public class TrackEventsKafkaConsumer {
    private final ArtistService artistService;
    private final AlbumService albumService;

    @KafkaHandler
    public void listenForAlbumCreation(AlbumCreatedEvent album) {
        try {
            log.info("Created album: " + album);
            albumService.save(album);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
    }

    @KafkaHandler
    public void listenForArtistCreation(ArtistCreatedEvent artist) {
        try {
            log.info("Created artist: " + artist);
            artistService.save(artist);
        } catch (Exception e) {
            log.error("Error: " + e.getMessage());
        }
    }

    @KafkaHandler(isDefault = true)
    public void defaultHandler(String message) {
        log.error("Unrecognized message: " + message);
    }
}
