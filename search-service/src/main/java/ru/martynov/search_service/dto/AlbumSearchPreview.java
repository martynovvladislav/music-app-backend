package ru.martynov.search_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class AlbumSearchPreview {
    @JsonProperty("id")
    public Long albumId;
    public String title;
    public Long artistId;
    public String artistName;
    public LocalDate releaseDate;
    public String coverUrl;
}
