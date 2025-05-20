package ru.martynov.music_service.application.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.martynov.music_service.domain.entity.Album;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MusicEventPublisherAdapter implements MusicEventPublisher  {
    private final MusicEventSpringProducer publisher;

    public void publishArtistCreationEvent(Artist artist) {
        ArtistCreatedEvent artistCreatedEvent = new ArtistCreatedEvent(
                this, artist.getArtistId(), artist.getName(), artist.getPhotoUrl()
        );

        publisher.publish(artistCreatedEvent);
    }

    public void publishAlbumCreationEvent(Album album, List<Track> tracks) {
        List<TrackCreatedEvent> trackEvents = mapToTrackEvents(tracks);

        AlbumCreatedEvent albumCreatedEvent = new AlbumCreatedEvent(
                this, album.getAlbumId(), album.getTitle(), album.getArtist().getArtistId(),
                album.getArtist().getName(), album.getReleaseDate(), album.getCoverUrl(), trackEvents
        );

        publisher.publish(albumCreatedEvent);
    }

    private List<TrackCreatedEvent> mapToTrackEvents(List<Track> tracks) {
        return tracks.stream()
                .map(this::mapToTrackEvent)
                .toList();
    }

    private TrackCreatedEvent mapToTrackEvent(Track track) {
        return new TrackCreatedEvent(
                this, track.getTrackId(), track.getTitle(), track.getArtist().getArtistId(), track.getArtist().getName(),
                track.getAlbum().getAlbumId(), track.getAlbum().getTitle(), track.getGenre().name(), track.getAudioUrl(),
                track.getCoverUrl(), track.getReleaseDate()
        );
    }
}
