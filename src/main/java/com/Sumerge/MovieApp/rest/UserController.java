package com.Sumerge.MovieApp.rest;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.response.UserResponse;
import com.Sumerge.MovieApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    private ResponseEntity<User> getUserDetails(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

//        @PostMapping("/register")
//        public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
//            userService.registerUser(userDto);
//            return ResponseEntity.ok("User registered successfully");
//        }
//
//        @PostMapping("/login")
//        public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginForm loginForm) {
//            String token = userService.loginUser(loginForm);
//            return ResponseEntity.ok(new JwtResponse(token));
//        }
 }


