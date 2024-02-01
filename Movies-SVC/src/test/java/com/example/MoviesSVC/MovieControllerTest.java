package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.controller.MovieController;
import com.example.MoviesSVC.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllMoviesTitles() throws Exception {
        // Mock the service response
        List<String> movieTitles = List.of("Title1", "Title2");
        when(movieService.getAllMovieTitles(0, 10)).thenReturn(movieTitles);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/movie-titles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(movieTitles.size()));
    }

    @Test
    public void testGetAllMovies() throws Exception {
        // Mock the service response using the helper method
        Page<Movie> moviePage = MovieControllerTestHelper.createMockMoviePage();
        when(movieService.getAllMovies(0, 10)).thenReturn(moviePage);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.length()").value(moviePage.getContent().size()));
    }

    @Test
    public void testGetMovieById() throws Exception {
        // Mock the service response
        Movie movie = new Movie(); // Set up your mocked Movie
        when(movieService.getMovieById(1L)).thenReturn(movie);

        // Perform the request and assert the response
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/movies/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(movie.getId()));
    }
}
