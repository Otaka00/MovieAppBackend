package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.auth.model.AuthenticationRequest;
import com.Sumerge.MovieApp.auth.model.AuthenticationResponse;
import com.Sumerge.MovieApp.auth.service.AuthenticationService;
import com.Sumerge.MovieApp.auth.model.RegisterRequest;
import com.Sumerge.MovieApp.auth.controller.AuthenticationController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Test
    public void testRegister() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        Mockito.when(authenticationService.register(Mockito.any(RegisterRequest.class)))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.register(registerRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testAuthenticate() {
        // Arrange
        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
        AuthenticationResponse expectedResponse = new AuthenticationResponse("token");

        Mockito.when(authenticationService.authenticate(Mockito.any(AuthenticationRequest.class)))
                .thenReturn(expectedResponse);

        // Act
        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    public void testValidate() {
        // Arrange
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer validToken");
        UserDetails expectedUserDetails = Mockito.mock(UserDetails.class);

        Mockito.when(authenticationService.validate(Mockito.any()))
                .thenReturn(expectedUserDetails);

        // Act
        ResponseEntity<UserDetails> responseEntity = authenticationController.validate(headers);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(expectedUserDetails, responseEntity.getBody());
    }
}

