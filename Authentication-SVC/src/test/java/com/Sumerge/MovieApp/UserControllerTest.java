package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.controller.UserController;
import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUserDetails() {
        // Arrange
        long userId = 1L;
        User mockUser = new User();
        when(userService.getUserById(userId)).thenReturn(mockUser);

        // Act
        ResponseEntity<User> responseEntity = userController.getUserDetails(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUser, responseEntity.getBody());
        verify(userService, times(1)).getUserById(userId);
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<User> mockUsers = Arrays.asList(new User(), new User());
        when(userService.getAllUsers()).thenReturn(mockUsers);

        // Act
        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockUsers, responseEntity.getBody());
        verify(userService, times(1)).getAllUsers();
    }
}

