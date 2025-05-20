package ru.martynov.music_service.application.event;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class AlbumCreatedEvent extends Event {
    private final Long albumId;
    private final String title;
    private final Long artistId;
    private final String artistName;
    private final LocalDate releaseDate;
    private final String coverUrl;
    private final List<TrackCreatedEvent> tracks;

    public AlbumCreatedEvent(
            Object source, Long albumId, String title, Long artistId, String artistName,
            LocalDate releaseDate, String coverUrl, List<TrackCreatedEvent> tracks
    ) {
        super(source);
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.artistName = artistName;
        this.releaseDate = releaseDate;
        this.coverUrl = coverUrl;
        this.tracks = tracks;
    }

    @Override
    public String getType() {
        return "ALBUM_CREATED";
    }
}
