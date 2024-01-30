package com.Sumerge.MovieApp.rest;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/users/{id}")
    private ResponseEntity<User> getUserDetails(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping("/users/")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }
}


