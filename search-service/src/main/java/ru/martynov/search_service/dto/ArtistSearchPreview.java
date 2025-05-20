package ru.martynov.search_service.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class ArtistSearchPreview {
    public Long artistId;
    public String name;
    public String photoUrl;
}
