package ru.martynov.music_service.application.query.getartist;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class AlbumPreview {
    public Long id;
    public String title;
    public Long artistId;
    public String artistName;
    public LocalDate releaseDate;
    public String coverUrl;
}
