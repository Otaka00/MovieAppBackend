package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper mapper;

    public User getUserById(long id) {
        Optional<User> user = userRepo.findById(id);
        User userResponse = mapper.map(user, User.class);
        return userResponse;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        return users.stream()
                .map(user -> mapper.map(user, User.class))
                .collect(Collectors.toList());
    }

}
