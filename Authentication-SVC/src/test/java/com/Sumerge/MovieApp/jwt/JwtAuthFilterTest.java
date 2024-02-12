package com.Sumerge.MovieApp.jwt;

import com.Sumerge.MovieApp.jwt.JwtAuthenticationFilter;
import com.Sumerge.MovieApp.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class JwtAuthFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

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
