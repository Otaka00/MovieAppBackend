package com.example.MoviesSVC;

import com.Sumerge.MovieApp.repository.UserRepository;
import com.example.MoviesSVC.configuration.ApplicationConfig;
import com.example.MoviesSVC.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApplicationConfigTest {

    private UserRepository repository;
    @Test
    public void testModelMapperBeanCreation() {

        // Arrange
        ApplicationConfig applicationConfig = new ApplicationConfig(repository);

        // Act
        ModelMapper modelMapper = applicationConfig.modelMapperBean();

        // Assert
        assertNotNull(modelMapper);
        // Add more assertions if needed
    }
}
