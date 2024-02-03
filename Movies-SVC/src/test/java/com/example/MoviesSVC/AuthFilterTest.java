package com.example.MoviesSVC;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthFilterTest {

//    @Mock
//    private RestTemplate restTemplate;

    @InjectMocks
    private AuthFilter authFilter;

//    @Test
//    public void testDoFilterInternal() throws Exception {
//        // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        FilterChain filterChain = mock(FilterChain.class);
//
//        when(request.getHeader("Authorization")).thenReturn("Bearer mockToken");
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth("mockToken");
//        ResponseEntity<String> authResponse = ResponseEntity.ok("mockUser");
//        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(), eq(String.class))).thenReturn(authResponse);
//
//        // Act
//        authFilter.doFilterInternal(request, response, filterChain);
//
//        // Assert
//        verify(filterChain).doFilter(request, response);
//    }

    @Test
    public void testDoFilterInternalValidToken() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("ValidToken");

        // Act
        authFilter.doFilterInternal(request, response, filterChain);

        // Assert
        verify(response).setStatus(403);
    }
}

