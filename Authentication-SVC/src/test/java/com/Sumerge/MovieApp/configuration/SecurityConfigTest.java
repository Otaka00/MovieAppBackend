package com.Sumerge.MovieApp.configuration;

import com.Sumerge.MovieApp.jwt.JwtAuthenticationFilter;
import com.Sumerge.MovieApp.configuration.SecurityConfig;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationProvider;

import static org.mockito.Mockito.verify;

class SecurityConfigTest {

    @Mock
    private JwtAuthenticationFilter jwtAuthFilter;

    @Mock
    private AuthenticationProvider authenticationProvider;

    @InjectMocks
    private SecurityConfig securityConfig;

}
