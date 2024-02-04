package com.Sumerge.MovieApp.controller;

import com.Sumerge.MovieApp.auth.AuthenticationRequest;
import com.Sumerge.MovieApp.auth.AuthenticationResponse;
import com.Sumerge.MovieApp.auth.AuthenticationService;
import com.Sumerge.MovieApp.auth.RegisterRequest;
import com.Sumerge.MovieApp.service.JwtService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDetails> validate(@RequestHeader HashMap<String, String> headers) {
        return ResponseEntity.ok(service.validate(headers));
    }
}
