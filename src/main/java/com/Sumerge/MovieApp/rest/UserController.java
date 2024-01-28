package com.Sumerge.MovieApp.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("/api/users")
    public class UserController {
        @Autowired
        private UserService userService;

        @PostMapping("/register")
        public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
            userService.registerUser(userDto);
            return ResponseEntity.ok("User registered successfully");
        }

        @PostMapping("/login")
        public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginForm loginForm) {
            String token = userService.loginUser(loginForm);
            return ResponseEntity.ok(new JwtResponse(token));
        }
 }


