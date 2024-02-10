package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.jwt.JwtService;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    @InjectMocks
    @Spy
    private JwtService jwtService;

    UserDetails user = User.builder()
            .username("testEmail")
            .password("password")
            .build();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void extractAllClaims_ValidToken_ReturnsClaims() {
        String token = generateValidToken();
        Claims claims = jwtService.extractAllClaims(token);
        assertNotNull(claims);
    }

    @Test
    void testIsTokenValid() {
        // Arrange
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        UserDetails userDetails = new User("testUser", "password", authorities);
        String token = jwtService.generateToken(new HashMap<>(), userDetails);

        // Act
        boolean result = jwtService.isTokenValid(token, userDetails);

        // Assert
        assertTrue(result);
    }

    @Test
    void isTokenExpired_ValidToken_ReturnsFalse() {
        String token = generateValidToken();
        assertFalse(jwtService.isTokenExpired(token));
    }
    @Test
    void isTokenValid_ValidTokenAndUserDetails_ReturnsTrue() {
        UserDetails userDetails = user;
        String token = jwtService.generateToken(userDetails);
        assertTrue(jwtService.isTokenValid(token, userDetails));
    }
    private String generateValidToken() {
        Map<String, Object> claims = new HashMap<>();
        return jwtService.generateToken(claims, User.builder()
                .username("testEmail")
                .password("password")
                .build());
    }
}
