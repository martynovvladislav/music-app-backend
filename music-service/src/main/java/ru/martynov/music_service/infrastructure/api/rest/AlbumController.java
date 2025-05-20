package ru.martynov.music_service.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.music_service.application.command.addalbum.AddAlbum;
import ru.martynov.music_service.application.query.getalbum.AlbumSummary;
import ru.martynov.music_service.application.query.getalbum.GetAlbumPageInfo;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.application.service.CommandService;
import ru.martynov.music_service.application.service.QueryService;

import java.net.URI;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final CommandService commandService;
    private final QueryService queryService;

    @PostMapping
    public ResponseEntity<?> addAlbum(@RequestBody AddAlbum addAlbumCommand) {
        Result<Void> result = commandService.execute(addAlbumCommand);

        if (result.isSuccess()) {
            return ResponseEntity.created(URI.create("")).build();
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ApiErrorResponse(result.getError()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlbumPageInfo(@PathVariable Long id) {
        Result<AlbumSummary> result = queryService.execute(new GetAlbumPageInfo(id));

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ApiErrorResponse(result.getError()));
        }
    }
}
