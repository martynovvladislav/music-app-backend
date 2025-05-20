package ru.martynov.search_service.kafka.event;

import java.time.LocalDate;
import java.util.List;

public class AlbumCreatedEvent {
    public Long albumId;
    public String title;
    public Long artistId;
    public String artistName;
    public LocalDate releaseDate;
    public String coverUrl;
    public List<TrackCreatedEvent> tracks;
}
