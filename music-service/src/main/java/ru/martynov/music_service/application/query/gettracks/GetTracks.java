package ru.martynov.music_service.application.query.gettracks;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.martynov.music_service.application.query.Query;

import java.util.List;

@Getter
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GetTracks implements Query {
    private List<Long> trackIds;
}
