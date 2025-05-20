package ru.martynov.music_service.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.martynov.music_service.domain.enumerator.Genre;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Track {
    private Long trackId;
    private String title;
    private Artist artist;
    private Album album;
    private Genre genre;
    private String audioUrl;
    private String coverUrl;
    private LocalDate releaseDate;

    public static Track create(String title, Artist artist, Album album,
                               Genre genre, String audioUrl, String coverUrl, LocalDate releaseDate) {
        return new Track(null, title, artist, album, genre, audioUrl, coverUrl, releaseDate);
    }

    public static Track create(Long id, String title, Artist artist, Album album,
                               Genre genre, String audioUrl, String coverUrl, LocalDate releaseDate) {
        return new Track(id, title, artist, album, genre, audioUrl, coverUrl, releaseDate);
    }
}
