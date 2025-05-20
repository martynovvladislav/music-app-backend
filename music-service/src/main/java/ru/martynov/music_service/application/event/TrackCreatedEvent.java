package ru.martynov.music_service.application.event;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TrackCreatedEvent extends Event {
    private final Long trackId;
    private final String title;
    private final Long artistId;
    private final String artistName;
    private final Long albumId;
    private final String albumTitle;
    private final String genre;
    private final String audioUrl;
    private final String coverUrl;
    private final LocalDate releaseDate;

    public TrackCreatedEvent(
            Object source, Long trackId, String title, Long artistId, String artistName, Long albumId,
            String albumTitle, String genre, String audioUrl, String coverUrl, LocalDate releaseDate
    ) {
        super(source);
        this.trackId = trackId;
        this.title = title;
        this.artistId = artistId;
        this.artistName = artistName;
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.genre = genre;
        this.audioUrl = audioUrl;
        this.coverUrl = coverUrl;
        this.releaseDate = releaseDate;
    }

    @Override
    public String getType() {
        return "TRACK_CREATED";
    }
}
