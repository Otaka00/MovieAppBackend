package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class MovieControllerTestHelper {

    public static Page<Movie> createMockMoviePage() {
        // Create a list of mocked movies
        List<Movie> movies = createMockMovies();

        // Create a PageRequest (pagination information)
        PageRequest pageRequest = PageRequest.of(0, 10);

        // Create a mocked Page<Movie> using PageImpl
        return new PageImpl<>(movies, pageRequest, movies.size());
    }

    private static List<Movie> createMockMovies() {
        List<Movie> movies = new ArrayList<>();
        // Add some mocked movies to the list
        movies.add(new Movie(1L, "Movie1", "Genre1", "Director1", "Description1", "poster1"));
        movies.add(new Movie(2L, "Movie2", "Genre2", "Director2", "Description2", "poster2"));
        // Add more movies as needed
        return movies;
    }
}
