package com.example.MoviesSVC;
//
//import com.example.MoviesSVC.controller.MovieController;
//import com.example.MoviesSVC.model.Movie;
//import com.example.MoviesSVC.service.MovieService;
//import com.github.tomakehurst.wiremock.client.WireMock;
//import com.github.tomakehurst.wiremock.junit5.WireMockTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//@WebMvcTest(controllers = MovieController.class)
//@AutoConfigureMockMvc
//@WireMockTest(httpPort = 8081)
//public class MovieControllerTest {
//
//    @Mock
//    private MovieService movieService;
//
//    @InjectMocks
//    private MovieController movieController;
//
//    @Autowired
//    private MockMvc mockMvc;
//    @BeforeEach
//    void setup() {
//        WireMock.reset();
//    }
//
//
//    @Test
//    public void testGetAllMoviesTitles() throws Exception {
//        // Mock the service response
//        List<String> movieTitles = List.of("Title1", "Title2");
//        when(movieService.getAllMovieTitles(0, 10)).thenReturn(movieTitles);
//
//        // Perform the request and assert the response
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/movie-titles")
//                        .param("page", "0")
//                        .param("size", "10")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
//        //                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(movieTitles.size()));
//    }
//
////    @Test
////    public void testGetAllMovies() throws Exception {
////        // Mock the service response using the helper method
////        Page<Movie> moviePage = MovieControllerTestHelper.createMockMoviePage();
////        when(movieService.getAllMovies(0, 10)).thenReturn(moviePage);
////
////        // Perform the request and assert the response
////        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies")
////                        .param("page", "0")
////                        .param("size", "10")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(moviePage.getContent().size()));
////    }
////
////    @Test
////    public void testGetMovieById() throws Exception {
////        // Mock the service response
////        Movie movie = new Movie(); // Set up your mocked Movie
////        when(movieService.getMovieById(1L)).thenReturn(movie);
////
////        // Perform the request and assert the response
////        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/1")
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(movie.getId()));
////    }
//}

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.service.MovieService;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.eclipse.jetty.http.HttpMethod;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

import java.util.Arrays;
import java.util.List;

//import static com.example.MoviesSVC.MovieControllerTestHelper.createMockMovies;
import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static WireMockServer wireMockServer;

    @BeforeAll
    public static void setUp() {
        wireMockServer = new WireMockServer();
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockServer.port());
    }

//    @AfterAll
//    public static void tearDown() {
//        wireMockServer.stop();
//    }

    @Test
    public void testGetAllMovies() {
        // Configure WireMock to stub the /api/v1/movies endpoint
        stubFor(get(urlEqualTo("/api/v1/movies"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("[{\"id\": 1, \"title\": \"Movie 1\"}, {\"id\": 2, \"title\": \"Movie 2\"}]")));

        // Make a request to your controller using the WireMock server
        ResponseEntity<Page<Movie>> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/v1/movies",
                null,
                new ParameterizedTypeReference<Page<Movie>>() {}); // Specify the expected type

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Movie> movies = response.getBody().getContent();
        assertEquals(2, movies.size());
        assertEquals("Movie 1", movies.get(0).getTitle());
        assertEquals("Movie 2", movies.get(1).getTitle());
    }
    @Test
    void testGetAllMoviesWithMockedAuthentication() throws Exception {
        // Set up WireMock to mock the validateToken endpoint
        WireMock.stubFor(WireMock.get(urlEqualTo("/api/v1/validate"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withBody("OK")));

        List<Movie> mockMovies = createMockMovies();
        Page<Movie> mockPage = new PageImpl<>(mockMovies);
        Mockito.when(movieService.getAllMovies(Mockito.any(), Integer.parseInt(Mockito.anyString()))).thenReturn(mockPage);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/movies")
                        .param("page", "0")
                        .param("size", "1")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer testToken")
                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value(mockMovies.get(0).getTitle()))
                .andExpect(jsonPath("$[0].genre").value(mockMovies.get(0).getGenre()));

        String jsonResponse = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("JSON Response: " + jsonResponse);
    }
    private List<Movie> createMockMovies() {
        return Arrays.asList(
                Movie.builder()
                        .id(1)
                        .title("Inception")
                        .genre("action")
                        .description("Nice Description")
                        .poster_path("llslls")
                        .build(),
                Movie.builder()
                        .build()
        );
    }
}
