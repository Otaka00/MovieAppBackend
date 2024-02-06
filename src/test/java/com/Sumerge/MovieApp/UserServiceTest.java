package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.repository.UserRepository;
import com.Sumerge.MovieApp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetUserById() {
        // Arrange
        long userId = 1L;
        User userInDatabase = new User(userId, "test@example.com", "password");

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(userInDatabase));
        when(modelMapperMock.map(userInDatabase, User.class)).thenReturn(null);

        // Act
        User resultUser = userService.getUserById(userId);

        // Assert
        assertNotNull(resultUser, "User should not be null");

        // Additional assertion to check against null values in the User object
        assertEquals(userInDatabase.getId(), resultUser.getId());
        assertEquals(userInDatabase.getEmail(), resultUser.getEmail());
        assertEquals(userInDatabase.getPass(), resultUser.getPass());

        verify(userRepositoryMock, times(1)).findById(userId);
        verify(modelMapperMock, times(1)).map(userInDatabase, User.class);
    }

    @Test
    void testGetAllUsers() {
        // Arrange
        List<User> usersInDatabase = Arrays.asList(new User(), new User(), new User());

        when(userRepositoryMock.findAll()).thenReturn(usersInDatabase);
        when(modelMapperMock.map(any(User.class), eq(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        List<User> resultUsers = userService.getAllUsers();

        // Assert
        assertEquals(usersInDatabase.size(), resultUsers.size());
        for (int i = 0; i < usersInDatabase.size(); i++) {
            assertEquals(usersInDatabase.get(i), resultUsers.get(i));
        }

        verify(userRepositoryMock, times(1)).findAll();
        verify(modelMapperMock, times(usersInDatabase.size())).map(any(User.class), eq(User.class));
    }
    @Test
    void testGetUserById_UserNotFound() {
        // Arrange
        long userId = 1L;

        when(userRepositoryMock.findById(userId)).thenReturn(Optional.empty());

        // Act
        User result = userService.getUserById(userId);

        // Assert
        assertEquals(null, result); // Adjust as per your application's logic
        verify(userRepositoryMock, times(1)).findById(userId);
    }
}
