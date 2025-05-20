package ru.martynov.music_service.infrastructure.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.martynov.music_service.domain.entity.Album;

import java.time.LocalDate;

@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlbumEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artist;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "cover_url", nullable = false)
    private String coverUrl;

    public static AlbumEntity create(Album album) {
        ArtistEntity artistEntity = ArtistEntity.fromDomainEntity(album.getArtist());
        return new AlbumEntity(null, album.getTitle(), artistEntity, album.getReleaseDate(), album.getCoverUrl());
    }

    public Album toDomainEntity() {
        return Album.create(id, title, artist.toDomainEntity(), releaseDate, coverUrl);
    }
}
