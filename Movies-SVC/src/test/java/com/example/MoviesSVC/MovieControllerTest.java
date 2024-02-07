package com.example.MoviesSVC;
import com.example.MoviesSVC.controller.MovieController;
import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.service.MovieService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@TestPropertySource(properties = {"movie.service.url= http://localhost:8081/api/v1/movies"})
@WebMvcTest(MovieController.class)
@ExtendWith(MockitoExtension.class)

//@WireMockTest(httpPort = 8081)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
        wireMockServer.start();
        WireMock.configureFor("localhost", 8081);
//        WireMock.reset();
    }

//    @AfterAll
//    static void tearDown() {
//        wireMockServer.stop();
//    }

    @Test
    void testGetAllMoviesWithWireMock() throws Exception {
        // Stub WireMock to return a predefined response
        wireMockServer.stubFor(WireMock.get(WireMock.urlEqualTo("/api/v1/movies"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("[{\"id\":1,\"title\":\"Movie 1\"},{\"id\":2,\"title\":\"Movie 2\"}]")));

        // Perform the actual MVC test
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Movie 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Movie 2"));
    }
    @Test
    void testGetMovieByIdWithoutAuthorization() throws Exception {
        // Assuming your controller has a method for getting a movie by ID

        // Mocking the behavior of the movieService
        when(movieService.getMovieById(anyLong())).thenReturn(
                new Movie(1L, "Movie Title", "Description", "Genre", "Director", "poster_path"));

        // Performing a GET request to "/movies/{id}"
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Movie Title"));
    }
}

//
//@SpringBootTest
//@AutoConfigureMockMvc
//@WireMockTest(httpPort = 8081)
//
//public class MovieControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private MovieService movieService;
//
//    private WireMockServer wireMockServer;
//
//    @BeforeEach
//    void setup() {
//        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8081));
//        wireMockServer.start();
//        WireMock.configureFor("localhost", 8081);
//    }
//
//    @Test
//    void testGetMoviesWithMockedAuthentication() throws Exception {
//        // Set up WireMock to mock the validateToken endpoint
//        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(200)
//                        .withBody("OK")));
//
//        List<Movie> mockMovies = createMockMovies();
//        Page<Movie> mockPage = new PageImpl<>(mockMovies);
//        Mockito.when(movieService.getAllMovies(Mockito.any(), Integer.parseInt(Mockito.anyString()))).thenReturn(mockPage);
//
//        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
//                        .param("page", "0")
//                        .param("size", "1")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer testToken")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value(mockMovies.get(0).getTitle()))
//                .andExpect(jsonPath("$[0].genre").value(mockMovies.get(0).getGenre()));
//
//        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println("JSON Response: " + jsonResponse);
//    }
//
//    @Test
//    void testGetMovieByIdWithMockedAuthentication() throws Exception {
//        int movieId = 1;
//
//        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(200)
//                        .withBody("OK")));
//
//        Movie mockMovie = createMockMovies().get(0);
//        Mockito.when(movieService.getMovieById(movieId));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", movieId)
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer testToken")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value(mockMovie.getTitle()))
//                .andExpect(jsonPath("$.genre").value(mockMovie.getGenre()));
//    }
//
//    @Test
//    void testGetMoviesUnauthorized() throws Exception {
//        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(403)
//                        .withBody("Unauthorized access")));
//
//        Mockito.when(movieService.getAllMovies(Mockito.any(), Integer.parseInt(Mockito.anyString())))
//                .thenThrow(new UnauthorizedAccessException("Unauthorized access"));
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/movies")
//                        .param("page", "0")
//                        .param("size", "10")
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer mockToken")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andExpect(content().string("Unauthorized access"));
//    }
//
//    @Test
//    void testGetMovieByIdUnauthorized() throws Exception {
//        int movieId = 1;
//
//        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
//                .willReturn(WireMock.aResponse()
//                        .withStatus(403)
//                        .withBody("Unauthorized access")));
//
//        Mockito.when(movieService.getMovieById(Mockito.eq(movieId)))
//                .thenThrow(new UnauthorizedAccessException("Unauthorized access"));
//
//        // Perform the request without a valid token for a specific movie and expect a 403 Forbidden response
//        mockMvc.perform(MockMvcRequestBuilders.get("/movies/{id}", movieId)
//                        .header(HttpHeaders.AUTHORIZATION, "Bearer mockToken")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isForbidden())
//                .andExpect(content().string("Unauthorized access"));
//    }
//
//    private List<Movie> createMockMovies() {
//        return Arrays.asList(
//                Movie.builder()
//                        .id(278)
//                        .description("Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison, where he puts his accounting skills to work for an amoral warden. During his long stretch in prison, Dufresne comes to be admired by the other inmates -- including an older prisoner named Red -- for his integrity and unquenchable sense of hope.")
//                        .poster_path("/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg")
//                        .genre("")
//                        .director("")
//                        .title("The Shawshank Redemption")
//                        .build(),
//                Movie.builder()
//                        .id(238)
//                        .title("The Godfather")
//                        .poster_path("/3bhkrj58Vtu7enYsRolD1fZdja1.jpg")
//                        .genre("1972-03-14")
//                        .director("The Godfather")
//                        .build()
//        );
//    }
//}