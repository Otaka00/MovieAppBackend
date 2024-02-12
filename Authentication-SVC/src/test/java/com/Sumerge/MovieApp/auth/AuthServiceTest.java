package com.Sumerge.MovieApp.auth;


import com.Sumerge.MovieApp.auth.model.AuthenticationRequest;
import com.Sumerge.MovieApp.auth.model.AuthenticationResponse;
import com.Sumerge.MovieApp.auth.model.RegisterRequest;
import com.Sumerge.MovieApp.auth.service.AuthenticationService;
import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.repository.UserRepository;
import com.Sumerge.MovieApp.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    public void testRegister() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("test@example.com", "password");
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Act
        AuthenticationResponse response = authenticationService.register(registerRequest);

        // Log the response for debugging
        System.out.println("Response: " + response);

        // Assert
        assertNotNull(response);
        verify(userRepository, times(1)).save(argThat(user -> "encodedPassword".equals(user.getPass())));
    }


    @Test
    public void testAuthenticate() {
        // Arrange
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("test@example.com", "password");
        User user = new User();
        when(userRepository.findByEmail(authenticationRequest.getEmail())).thenReturn(Optional.of(user));
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(jwtService.generateToken(user)).thenReturn("jwtToken");

        // Act
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getToken());
        verify(userRepository, times(1)).findByEmail(authenticationRequest.getEmail());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(user);
    }

    @Test
    public void testValidate() {
        // Arrange
        HashMap<String, String> headers = new HashMap<>();
        headers.put("authorization", "Bearer jwtToken");
        String userEmail = "test@example.com";
        when(jwtService.extractUserName("jwtToken")).thenReturn(userEmail);
        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(new User()));

        // Act
        UserDetails userDetails = authenticationService.validate(headers);

        // Assert
        assertNotNull(userDetails);
        verify(jwtService, times(1)).extractUserName("jwtToken");
        verify(userRepository, times(1)).findByEmail(userEmail);
    }
}

