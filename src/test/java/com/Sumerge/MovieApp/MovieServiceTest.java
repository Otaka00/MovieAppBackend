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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
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
        // Mock data
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(1L, "Movie1", "Genre1", "Director1", "Description1"),
                new Movie(2L, "Movie2", "Genre2", "Director2", "Description2")
        );

        // Mock repository response
        when(movieRepo.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(expectedMovies));

        // Mock mapper response
        when(mapper.map(any(), eq(Movie.class)))
                .thenAnswer(invocation -> {
                    Movie sourceMovie = invocation.getArgument(0);
                    // Simulate mapping by returning a new Movie with the same title
                    return new Movie(null, sourceMovie.getTitle(), null, null, null);
                });

        // Call the service method
        List<Movie> result = movieService.getAllMovies(0, 10).getContent();

        // Verify the result
        assertEquals(expectedMovies.size(), result.size());
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));
    }
    @Test
    void testGetAllMoviesFirstPage() {
        int page = 0;
        int size = 10;
        List<Movie> expectedMovies = Collections.singletonList(new Movie());
        Page<Movie> pageResult = new PageImpl<>(expectedMovies);
        when(movieRepo.findAll(PageRequest.of(page, size))).thenReturn(pageResult);
        when(mapper.map(any(), eq(Movie.class))).thenReturn(new Movie());

        List<Movie> result = movieService.getAllMovies(page, size).getContent();

        assertEquals(expectedMovies, result);
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));
    }
    @Test
    void testGetAllMoviesEmptyPage() {
        int page = 2;
        int size = 10;
        List<Movie> expectedMovies = Collections.emptyList();
        Page<Movie> pageResult = new PageImpl<>(expectedMovies);
        when(movieRepo.findAll(PageRequest.of(page, size))).thenReturn(pageResult);

        List<Movie> result = movieService.getAllMovies(page, size).getContent();

        assertEquals(expectedMovies, result);
    }
}

