package com.martynov.user_service.user.application.dto.music;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrackDto {
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
