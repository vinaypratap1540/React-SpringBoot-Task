package com.esd.moviebooking.controller;

import com.esd.moviebooking.dto.MovieResponse;
import com.esd.moviebooking.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> search(
            @RequestParam String genre,
            @RequestParam String language) {
        return ResponseEntity.ok(movieService.search(genre, language));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }
}
