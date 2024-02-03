package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Mock
    private Movie movie;

    @BeforeEach
    public void setUp() {
//        // Initialize the entityManagerFactory (use your persistence unit name)
//        entityManagerFactory = Persistence.createEntityManagerFactory("test-unit");
//
//        // Create an entityManager from the factory
//        entityManager = entityManagerFactory.createEntityManager();

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMovieGettersAndSetters() {
        // Arrange
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setDirector("Test Director");
        movie.setDescription("Test Description");

        // Act
        Long id = movie.getId();
        String title = movie.getTitle();
        String genre = movie.getGenre();
        String director = movie.getDirector();
        String description = movie.getDescription();

        // Assert
        assertEquals(1L, id);
        assertEquals("Test Movie", title);
        assertEquals("Action", genre);
        assertEquals("Test Director", director);
        assertEquals("Test Description", description);
    }

    @Test
    public void testMoviePersistence() {
        // Arrange
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Test Movie");
        movie.setGenre("Action");
        movie.setDirector("Test Director");
        movie.setDescription("Test Description");

        // Act
        entityManager.persist(movie);

        // Assert
        Movie retrievedMovie = entityManager.find(Movie.class, 1L);
        assertEquals("Test Movie", retrievedMovie.getTitle());
        assertEquals("Action", retrievedMovie.getGenre());
        assertEquals("Test Director", retrievedMovie.getDirector());
        assertEquals("Test Description", retrievedMovie.getDescription());
    }

    @Test
    public void testMovieWithMockTitle() {
        // Arrange
        String mockedTitle = "Mocked Movie Title";
        when(movie.getTitle()).thenReturn(mockedTitle);

        // Act
        String title = movie.getTitle();

        // Assert
        assertEquals(mockedTitle, title);
        verify(movie, times(1)).getTitle();
    }
    @Test
    public void testMovieWithMockGenre() {
        // Arrange
        String mockedGenre = "Mocked Genre";
        when(movie.getGenre()).thenReturn(mockedGenre);

        // Act
        String genre = movie.getGenre();

        // Assert
        assertEquals(mockedGenre, genre);
        verify(movie, times(1)).getGenre();
    }
}
