package com.esd.moviebooking.service;

import com.esd.moviebooking.dto.MovieResponse;
import com.esd.moviebooking.entity.Movie;
import com.esd.moviebooking.exception.ResourceNotFoundException;
import com.esd.moviebooking.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieResponse> getAll() {
        return movieRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<MovieResponse> search(String genre, String language) {
        return movieRepository.searchMovies(genre, language)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MovieResponse getById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found: " + id));
        return toResponse(movie);
    }

    private MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(), movie.getTitle(), movie.getGenre(), movie.getLanguage(),
                movie.getDuration(), movie.getTotalSeats(), movie.getAvailableSeats());
    }
}
