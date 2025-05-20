package ru.martynov.music_service.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.music_service.application.command.addartist.AddArtist;
import ru.martynov.music_service.application.query.getartist.ArtistSummary;
import ru.martynov.music_service.application.query.getartist.GetArtistPageInfo;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.application.service.CommandService;
import ru.martynov.music_service.application.service.QueryService;
import ru.martynov.music_service.infrastructure.api.rest.ApiErrorResponse;

import java.net.URI;

@RestController
@RequestMapping("/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final CommandService commandService;
    private final QueryService queryService;

    @PostMapping
    public ResponseEntity<?> addArtist(@RequestBody AddArtist addArtistCommand) {
        Result<Void> result = commandService.execute(addArtistCommand);

        if (result.isSuccess()) {
            return ResponseEntity.created(URI.create("")).build();
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ApiErrorResponse(result.getError()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArtistPageInfo(@PathVariable Long id) {
        Result<ArtistSummary> result = queryService.execute(new GetArtistPageInfo(id));

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ApiErrorResponse(result.getError()));
        }
    }
}
