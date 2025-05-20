package ru.martynov.music_service.infrastructure.persistance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.martynov.music_service.domain.entity.Artist;

@Entity
@Table(name = "artist")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArtistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(nullable = false, name = "photo_url")
    private String photoUrl;

    public Artist toDomainEntity() {
        return Artist.create(id, name, bio, photoUrl);
    }
    public static ArtistEntity fromDomainEntity(Artist artist) {
        return new ArtistEntity(artist.getArtistId(), artist.getName(), artist.getBio(), artist.getPhotoUrl());
    }
}
