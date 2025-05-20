package ru.martynov.music_service.application.query.getalbum;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ru.martynov.music_service.domain.enumerator.Genre;

import java.time.LocalDate;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TrackSummary {
    public Long trackId;
    public String title;
    public Long artistId;
    public String artistName;
    public Long albumId;
    public String albumTitle;
    public Genre genre;
    public String audioUrl;
    public String coverUrl;
    public LocalDate releaseDate;
}
