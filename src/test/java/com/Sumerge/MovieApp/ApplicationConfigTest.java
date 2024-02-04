package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.configuration.ApplicationConfig;
import com.Sumerge.MovieApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ApplicationConfigTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private ApplicationConfig applicationConfig;

    @Test
    public void testUserDetailsServiceBean() {
        // Act
        UserDetailsService userDetailsService = applicationConfig.userDetailsService();

        // Assert
        assertNotNull(userDetailsService);
    }

    @Test
    public void testPasswordEncoderBean() {
        // Act
        PasswordEncoder passwordEncoder = applicationConfig.passwordEncoder();

        // Assert
        assertNotNull(passwordEncoder);
    }
}
