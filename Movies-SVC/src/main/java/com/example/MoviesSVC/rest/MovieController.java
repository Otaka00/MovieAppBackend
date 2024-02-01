package com.example.MoviesSVC.rest;

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.service.MovieService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;

@GetMapping()
//@PreAuthorize("isAuthenticated()")
    public ResponseEntity<Page<Movie>> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Movie> movies = movieService.getAllMovies(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @GetMapping("/movie-titles")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<String>> getAllMoviesTitles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<String> movies = movieService.getAllMovieTitles(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }
    @GetMapping("/{id}")
//    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Movie> getMovieById(@PathVariable("id") long id) {
        Movie movie = movieService.getMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}

