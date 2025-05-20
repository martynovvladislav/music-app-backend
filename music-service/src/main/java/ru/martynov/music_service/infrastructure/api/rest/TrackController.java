package ru.martynov.music_service.infrastructure.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.music_service.application.query.getartist.ArtistSummary;
import ru.martynov.music_service.application.query.gettracks.GetTracks;
import ru.martynov.music_service.application.query.gettracks.TracksInfo;
import ru.martynov.music_service.application.result.Result;
import ru.martynov.music_service.application.service.QueryService;

@RestController
@RequestMapping("/tracks")
@RequiredArgsConstructor
public class TrackController {
    private final QueryService queryService;

    @PostMapping("/enrich")
    public ResponseEntity<?> getTracks(@RequestBody GetTracks getTracks) {
        Result<TracksInfo> result = queryService.execute(getTracks);

        if (result.isSuccess()) {
            return ResponseEntity.ok(result.getData());
        } else {
            return ResponseEntity.internalServerError()
                    .body(new ApiErrorResponse(result.getError()));
        }
    }
}
