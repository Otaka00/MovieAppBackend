package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ModelMapper mapper;

    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
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
