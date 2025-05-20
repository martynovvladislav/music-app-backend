package ru.martynov.music_service.application.query.gettracks;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import ru.martynov.music_service.application.query.getalbum.TrackSummary;

import java.util.List;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
public class TracksInfo {
    public List<TrackSummary> tracks;
}
