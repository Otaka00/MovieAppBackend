package com.example.MoviesSVC.wiremock;
import com.example.MoviesSVC.filter.AuthFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AuthFilterTest {

    @InjectMocks
    private AuthFilter authFilter;

    @Value("${auth.filter.authorization-header}")
    private String AUTHORIZATION_HEADER;

    @Value("${auth.filter.token-prefix}")
    private String TOKEN_PREFIX;

    @Value("${auth.filter.auth-url}")
    private String AUTH_URL;

    @BeforeEach
    void init(){
        authFilter.setAUTHORIZATION_HEADER(AUTHORIZATION_HEADER);
        authFilter.setAUTH_URL(AUTH_URL);
        authFilter.setTOKEN_PREFIX(TOKEN_PREFIX);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoFilterInternalValidToken() throws Exception {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        when(request.getHeader("Authorization")).thenReturn("ValidToken");

        // Act
        authFilter.doFilter(request, response, filterChain);

        // Assert
        verify(response).setStatus(403);
    }
}

