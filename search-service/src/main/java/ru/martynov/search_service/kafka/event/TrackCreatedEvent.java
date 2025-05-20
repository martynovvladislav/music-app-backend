package ru.martynov.search_service.kafka.event;

import java.time.LocalDate;

public class TrackCreatedEvent {
    public Long trackId;
    public String title;
    public Long artistId;
    public String artistName;
    public Long albumId;
    public String albumTitle;
    public String genre;
    public String audioUrl;
    public String coverUrl;
    public LocalDate releaseDate;
}
