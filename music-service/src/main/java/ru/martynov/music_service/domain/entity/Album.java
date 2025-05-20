package ru.martynov.music_service.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Album {
    private Long albumId;
    private String title;
    private Artist artist;
    private LocalDate releaseDate;
    private String coverUrl;

    public static Album create(String title, Artist artist, LocalDate releaseDate, String coverUrl) {
        return new Album(null, title, artist, releaseDate, coverUrl);
    }

    public static Album create(Long id, String title, Artist artist, LocalDate releaseDate, String coverUrl) {
        return new Album(id, title, artist, releaseDate, coverUrl);
    }
}
