package ru.martynov.search_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TrackSearchSummary {
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
