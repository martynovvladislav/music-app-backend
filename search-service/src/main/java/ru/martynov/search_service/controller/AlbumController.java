package ru.martynov.search_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.martynov.search_service.dto.AlbumSearchPreview;
import ru.martynov.search_service.service.AlbumService;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping("/search")
    public ResponseEntity<List<AlbumSearchPreview>> searchByTitle(@RequestParam String prefix) {
        return ResponseEntity.ok(albumService.searchByTitle(prefix));
    }
}
