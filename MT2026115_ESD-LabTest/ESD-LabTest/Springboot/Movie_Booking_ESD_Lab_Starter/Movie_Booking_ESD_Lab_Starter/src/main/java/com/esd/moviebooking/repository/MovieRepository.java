package com.esd.moviebooking.repository;

import com.esd.moviebooking.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("""
            SELECT m FROM Movie m
            WHERE m.genre = :genre AND m.language = :language
            """)
    List<Movie> searchMovies(
            @Param("genre") String genre,
            @Param("language") String language);
}
