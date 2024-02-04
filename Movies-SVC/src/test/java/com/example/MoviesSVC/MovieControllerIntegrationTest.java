package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.repository.MovieRepository;
import com.example.MoviesSVC.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MovieControllerIntegrationTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private MovieRepository movieRepo;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetAllMovies() {
        // Mock authentication
//        mockAuthentication();

        // Mock data
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(1L, "Movie1", "Genre1", "Director1", "Description1", "poster1"),
                new Movie(2L, "Movie2", "Genre2", "Director2", "Description2", "poster2")
                // Add more movies as needed
        );

        // Mock repository response
        when(movieRepo.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(expectedMovies));

        // Mock mapper response
        when(mapper.map(any(), eq(Movie.class)))
                .thenAnswer(invocation -> {
                    Movie sourceMovie = invocation.getArgument(0);
                    // Simulate mapping by returning a new Movie with the same title
                    return new Movie(1L, sourceMovie.getTitle(), null, null, null, null);
                });

        // Call the service method
        List<Movie> result = movieService.getAllMovies(0, 10).getContent(); // Assuming 0-based page index and page size of 10

        // Verify the result
        assertEquals(expectedMovies.size(), result.size());
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));
    }

//    private void mockAuthentication() {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//    }

}
