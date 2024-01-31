package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.model.Movie;
import com.Sumerge.MovieApp.repository.MovieRepo;
import com.Sumerge.MovieApp.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepo movieRepo;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private MovieService movieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMovieById() {
        long movieId = 1L;
        Movie expectedMovie = new Movie();
        when(movieRepo.findById(movieId)).thenReturn(Optional.of(expectedMovie));
        when(mapper.map(any(), eq(Movie.class))).thenReturn(expectedMovie);

        Movie result = movieService.getMovieById(movieId);

        assertEquals(expectedMovie, result);
        verify(mapper, times(1)).map(any(), eq(Movie.class));
    }

    @Test
    void testGetAllMovies() {
        List<Movie> expectedMovies = Arrays.asList(new Movie(), new Movie());

        // Configure the movieRepo to return the expected list of movies when findAll is called
        when(movieRepo.findAll()).thenReturn(expectedMovies);

        // Configure the mapper to return the expected movies when map is called
        when(mapper.map(any(), eq(Movie.class))).thenReturn(new Movie());

        List<Movie> result = movieService.getAllMovies();

        assertEquals(expectedMovies, result);

        // Verify that map method is called for each movie in the list
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));

    }
}

