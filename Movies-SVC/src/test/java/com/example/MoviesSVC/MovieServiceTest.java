package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.repository.MovieRepository;
import com.example.MoviesSVC.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepo;

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
        assertThat(result).isNotNull();
        assertEquals(expectedMovies.size(), result.size());
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));
    }

//    @Test
//    public void testGetAllMovieTitles() {
//        // Arrange
//        int page = 0;
//        int size = 10;
//        PageRequest pageRequest = PageRequest.of(page, size);
//        List<Movie> movies = List.of(
//                new Movie(1L, "Movie 1", "Action", "Director 1", "Description 1"),
//                new Movie(2L, "Movie 2", "Drama", "Director 2", "Description 2")
//        );
//
//        Page<Movie> moviePage = mock(Page.class);
//        when(movieRepo.findAll(pageRequest)).thenReturn(moviePage);
//        when(moviePage.map(Movie::getTitle)).thenReturn(((Page<String>) List.of("Movie 1", "Movie 2"));
//
//        // Act
//        List<String> result = movieService.getAllMovieTitles(page, size);
//
//        // Assert
//        assertEquals(List.of("Movie 1", "Movie 2"), result);
//        verify(movieRepo, times(1)).findAll(pageRequest);
//    }

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

