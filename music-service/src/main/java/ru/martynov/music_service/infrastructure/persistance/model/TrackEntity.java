package ru.martynov.music_service.infrastructure.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.martynov.music_service.domain.entity.Artist;
import ru.martynov.music_service.domain.entity.Track;
import ru.martynov.music_service.domain.enumerator.Genre;

import java.time.LocalDate;

@Entity
@Table(name = "track")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrackEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long trackId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artist;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumEntity album;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    @Column(nullable = false, name = "audio_url")
    private String audioUrl;

    @Column(nullable = false, name = "cover_url")
    private String coverUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    public Track toDomainEntity() {
        return Track.create(trackId, title, artist.toDomainEntity(), album.toDomainEntity(), genre, audioUrl, coverUrl, releaseDate);
    }

    public static TrackEntity fromDomainEntity(Track track, AlbumEntity album, ArtistEntity artist) {
        return new TrackEntity(
                track.getTrackId(), track.getTitle(), artist,
                album, track.getGenre(), track.getAudioUrl(), track.getCoverUrl(), track.getReleaseDate()
        );
    }
}

