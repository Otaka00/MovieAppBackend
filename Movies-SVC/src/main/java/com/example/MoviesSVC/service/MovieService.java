package com.example.MoviesSVC.service;

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.repository.MovieRepo;
import com.example.MoviesSVC.configuration.ApplicationConfig;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ModelMapper mapper;

    public Movie getMovieById(long id) {
        Optional<Movie> movie = movieRepo.findById(id);
        Movie movieResponse = mapper.map(movie, Movie.class);
        return movieResponse;
    }

public Page<Movie> getAllMovies(int page, int size) {
    return movieRepo.findAll(PageRequest.of(page, size))
            .map(movie -> mapper.map(movie, Movie.class));
}


    public List<String> getAllMovieTitles(int page, int size) {
        return movieRepo.findAll(PageRequest.of(page, size))
                .map(Movie::getTitle)
                .toList();
    }

    public Movie addMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public List<Movie> getAllMoviesForAuthenticatedUser() {
        // Get the email of the authenticated user
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Fetch movies based on the user (you might have a User entity linked to Movie entity)
        // Assuming there's a method like findByUserEmail in your MovieRepo
        return movieRepo.findByUserEmail(userEmail);
    }

}
