package ru.martynov.music_service.application.query.getartist;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class ArtistSummary {
    public Long artistId;
    public String name;
    public String bio;
    public String photoUrl;
    public List<AlbumPreview> albums;
}
