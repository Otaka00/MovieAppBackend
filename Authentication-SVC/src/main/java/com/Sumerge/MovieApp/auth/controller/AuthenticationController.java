package com.Sumerge.MovieApp.auth.controller;

import com.Sumerge.MovieApp.auth.model.AuthenticationRequest;
import com.Sumerge.MovieApp.auth.model.AuthenticationResponse;
import com.Sumerge.MovieApp.auth.service.AuthenticationService;
import com.Sumerge.MovieApp.auth.model.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDetails> validate(@RequestHeader HashMap<String, String> headers) {
        return ResponseEntity.ok(authService.validate(headers));
    }
}
