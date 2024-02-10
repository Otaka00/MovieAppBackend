package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public User getUserById(long id) {
        Optional<User> user = userRepository.findById(id);
        User userResponse = mapper.map(user, User.class);
        return userResponse;
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> mapper.map(user, User.class))
                .collect(Collectors.toList());
    }

}
