package com.example.MoviesSVC.configuration;
import com.example.MoviesSVC.configuration.ApplicationConfig;
import com.example.MoviesSVC.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationConfigTest {

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @Test
    public void testMovieServiceBean() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Act
        MovieService movieService = applicationConfig.movieBean();

        // Assert
        assertNotNull(movieService);
    }

    @Test
    public void testModelMapperBean() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);

        // Act
        ModelMapper modelMapper = applicationConfig.modelMapperBean();

        // Assert
        assertNotNull(modelMapper);
    }
}
