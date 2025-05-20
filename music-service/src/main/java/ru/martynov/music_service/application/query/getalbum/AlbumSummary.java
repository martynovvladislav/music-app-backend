package ru.martynov.music_service.application.query.getalbum;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class AlbumSummary {
    public Long id;
    public Long artistId;
    public String artistName;
    public String title;
    public LocalDate releaseDate;
    public String coverUrl;
    public List<TrackSummary> tracks;
}
