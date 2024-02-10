package com.Sumerge.MovieApp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.Sumerge.MovieApp.entity.User;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void testUserGettersAndSetters() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPass("password");

        // Act
        Long id = user.getId();
        String email = user.getEmail();
        String pass = user.getPass();

        // Assert
        assertEquals(1L, id);
        assertEquals("test@example.com", email);
        assertEquals("password", pass);
    }

    @Test
    void testUserEqualsAndHashCode() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("test@example.com");
        user1.setPass("password");

        User user2 = new User();
        user2.setId(1L);
        user2.setEmail("test@example.com");
        user2.setPass("password");

        // Act & Assert
        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    void testUserToString() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setPass("password");

        // Act
        String toStringResult = user.toString();

        // Assert
        assertTrue(toStringResult.contains("id=1"));
        assertTrue(toStringResult.contains("email=test@example.com"));
        assertTrue(toStringResult.contains("pass=password"));
    }
}
