package ru.martynov.music_service.application.query.getalbum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.martynov.music_service.application.query.Query;

@Getter
@AllArgsConstructor
public class GetAlbumPageInfo implements Query {
    private Long albumId;
}
