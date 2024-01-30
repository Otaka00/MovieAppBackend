package com.Sumerge.MovieApp.rest;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.model.Movie;
import com.Sumerge.MovieApp.service.MovieService;
import com.Sumerge.MovieApp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api/v1")
public class MovieController {

        private final MovieService movieService;
        private final UserService userService;

        @GetMapping("/users/{userId}")
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<User> getUserData(@PathVariable("userId") Long userId) {
            User user = userService.getUserById(userId);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }

    @GetMapping("/movies/")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

        @ExceptionHandler({EntityNotFoundException.class})
        public ResponseEntity<String> handleNotFoundException(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

