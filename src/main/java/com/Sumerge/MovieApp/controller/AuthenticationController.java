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
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<UserDetails> validate(@RequestHeader HashMap<String, String> headers) {
        return ResponseEntity.ok(service.validate(headers));
    }
//    @PostMapping("validate-token")
//    public ResponseEntity<Map<String, Object>> validateToken(@RequestBody Map<String, String> tokenMap) {
//        String token = tokenMap.get("token");
//
//        try {
//            // Parse the token and validate its signature
//            Claims claims = Jwts.parser()
//                    .setSigningKey(jwtService.getSecretKey())
//                    .parseClaimsJws(token)
//                    .getBody();
//
//            // If the token is valid, return a success response
//            Map<String, Object> response = new HashMap<>();
//            response.put("valid", true);
//            return ResponseEntity.ok(response);
//
//        } catch (ExpiredJwtException ex) {
//            // Token has expired
//            return createErrorResponse("Token expired", HttpStatus.UNAUTHORIZED);
//
//        } catch (UnsupportedJwtException | MalformedJwtException | SignatureException ex) {
//            // Token is invalid or has been tampered with
//            return createErrorResponse("Invalid token", HttpStatus.UNAUTHORIZED);
//
//        } catch (Exception ex) {
//            // Other exceptions
//            return createErrorResponse("Error validating token", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    private ResponseEntity<Map<String, Object>> createErrorResponse(String message, HttpStatus status) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("error", message);
//        return ResponseEntity.status(status).body(response);
//    }
}
