package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.repository.UserRepo;
import com.Sumerge.MovieApp.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper mapper;

    public UserResponse getUserById(long id) {
        Optional<User> user = userRepo.findById(id);
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        return userResponse;
    }

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
