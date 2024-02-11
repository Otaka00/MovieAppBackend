package com.example.MoviesSVC;

import com.example.MoviesSVC.controller.MovieController;
import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.repository.MovieRepository;
import com.example.MoviesSVC.service.MovieService;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MovieControllerIntegrationTest extends WireMockTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private MovieRepository movieRepo;

    @InjectMocks
    private MovieService movieService;

    @Test
    void testGetAllMovies() {
        // Mock authentication
        mockAuthentication();

        // Mock data
        List<Movie> expectedMovies = Arrays.asList(
                new Movie(1L, "Movie1", "Genre1", "Director1", "Description1", "poster1"),
                new Movie(2L, "Movie2", "Genre2", "Director2", "Description2", "poster2")
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
        List<Movie> result = movieService.getAllMovies(0, 10).getContent();

        // Verify the result
        assertEquals(expectedMovies.size(), result.size());
        verify(mapper, times(expectedMovies.size())).map(any(), eq(Movie.class));
    }

    private void mockAuthentication() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    void testGetAllMoviesWired() {

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/api/v1/movies"))
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\":1,\"title\":\"Movie 1\"},{\"id\":2,\"title\":\"Movie 2\"}]")));

        // Use RestTemplate to make a request to the WireMock server
        String baseUrl = "http://localhost:8081";
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(baseUrl + "/api/v1/movies", String.class);

        // Verify that the response is as expected
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("[{\"id\":1,\"title\":\"Movie 1\"},{\"id\":2,\"title\":\"Movie 2\"}]", responseEntity.getBody());
    }
}
