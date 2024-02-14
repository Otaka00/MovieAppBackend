package com.Sumerge.MovieApp.jwt;

import com.Sumerge.MovieApp.jwt.JwtAuthenticationFilter;
import com.Sumerge.MovieApp.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class JwtAuthFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${auth.filter.authorization-header}")
    private String AUTHORIZATION_HEADER;

    @Value("${auth.filter.token-prefix}")
    private String TOKEN_PREFIX;
    @BeforeEach
    void init(){
        jwtAuthenticationFilter.setAUTHORIZATION_HEADER(AUTHORIZATION_HEADER);
        jwtAuthenticationFilter.setTOKEN_PREFIX(TOKEN_PREFIX);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternalValidToken() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);
        UserDetails userDetails = Mockito.mock(UserDetails.class);

        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer validToken");
        Mockito.when(jwtService.extractUserName("validToken")).thenReturn("userEmail");
        Mockito.when(userDetailsService.loadUserByUsername("userEmail")).thenReturn(userDetails);
        Mockito.when(jwtService.isTokenValid("validToken", userDetails)).thenReturn(true);

        // Mock SecurityContextHolder
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        Mockito.verify(filterChain, Mockito.times(1)).doFilter(request, response);
        Mockito.verify(securityContext, Mockito.times(1))
                .setAuthentication(Mockito.any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    public void testDoFilterInternalInvalidToken() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        FilterChain filterChain = Mockito.mock(FilterChain.class);

        Mockito.when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        Mockito.when(jwtService.extractUserName("invalidToken")).thenReturn(null);

        // Mock SecurityContextHolder
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        // Act
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // Assert
        Mockito.verify(filterChain, Mockito.times(1)).doFilter(request, response);
        Mockito.verify(securityContext, Mockito.never())
                .setAuthentication(Mockito.any(UsernamePasswordAuthenticationToken.class));
    }
}
