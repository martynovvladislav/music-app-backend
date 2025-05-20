package ru.martynov.search_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.search_service.dto.TrackSearchSummary;
import ru.martynov.search_service.service.TrackService;

import java.util.List;

@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService trackService;

    @GetMapping("/search")
    public ResponseEntity<List<TrackSearchSummary>> searchByTitle(@RequestParam String prefix) {
        return ResponseEntity.ok(trackService.searchByTitle(prefix));
    }
}
