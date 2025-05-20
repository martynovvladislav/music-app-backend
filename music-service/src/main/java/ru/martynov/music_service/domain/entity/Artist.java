package ru.martynov.music_service.domain.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Artist {
    private Long artistId;
    private String name;
    private String bio;
    private String photoUrl;

    public static Artist create(String name, String bio, String photoUrl) {
        return new Artist(null, name, bio, photoUrl);
    }

    public static Artist create(Long id, String name, String bio, String photoUrl) {
        return new Artist(id, name, bio, photoUrl);
    }
}
