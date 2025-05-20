package ru.martynov.search_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.search_service.dto.ArtistSearchPreview;
import ru.martynov.search_service.service.ArtistService;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/search")
    public ResponseEntity<List<ArtistSearchPreview>> searchByName(@RequestParam String prefix) {
        return ResponseEntity.ok(artistService.searchByName(prefix));
    }
}
