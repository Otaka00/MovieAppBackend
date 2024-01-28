package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    public void registerUser(UserDto userDto) {
//        // Implement user registration logic
//    }
//
//    public String loginUser(LoginForm loginForm) {
//        // Implement user login logic
//        // Use authenticationManager.authenticate and jwtTokenProvider.generateToken
//        String token= "kk";
//        return token;
//    }
}
