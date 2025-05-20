package ru.martynov.music_service.application.command.addalbum;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.martynov.music_service.application.command.Command;
import ru.martynov.music_service.domain.enumerator.Genre;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddAlbum implements Command {
    private String title;
    private Long artistId;
    private LocalDate releaseDate;
    private String coverUrl;
    private List<TrackInfo> tracks;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    public static class TrackInfo {
        private String title;
        private Genre genre;
        private String audioUrl;
        private String coverUrl;
    }
}
