package ru.martynov.music_service.application.query.getartist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.martynov.music_service.application.query.Query;

@Getter
@AllArgsConstructor
public class GetArtistPageInfo implements Query {
    private Long artistId;
}
