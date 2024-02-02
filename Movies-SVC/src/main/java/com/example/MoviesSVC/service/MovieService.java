package com.example.MoviesSVC.service;

import com.example.MoviesSVC.client.AuthenticationServiceFeignClient;
import com.example.MoviesSVC.model.Movie;
import com.example.MoviesSVC.repository.MovieRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private AuthenticationServiceFeignClient authServiceFeignClient;

    private static final String AUTHORIZATION_HEADER = "Authorization";


    public Movie getMovieById(long id) {
        Optional<Movie> movie = movieRepo.findById(id);
        Movie movieResponse = mapper.map(movie, Movie.class);
        return movieResponse;
    }

public Page<Movie> getAllMovies(int page, int size) {
    return movieRepo.findAll(PageRequest.of(page, size))
            .map(movie -> mapper.map(movie, Movie.class));
}

    public List<String> getAllMovieTitles(int page, int size) {
        return movieRepo.findAll(PageRequest.of(page, size))
                .map(Movie::getTitle)
                .toList();
    }

    private void validateToken(@NonNull HttpServletRequest request) {
        String authHeader = request.getHeader(AUTHORIZATION_HEADER);
        String jwt = authHeader.substring(7);
        Map<String, String> tokenMap = Collections.singletonMap("token", jwt);
        Map<String, Object> validationResponse = authServiceFeignClient.validateToken(tokenMap);

//        if (!(boolean) validationResponse.get("valid")) {
//            throw new Exception("Invalid token");
//        }
    }


//    private void validateToken() {
//        String token =  SecurityContextHolder.getContext().getAuthentication().getName();
//        Map<String, String> tokenMap = Collections.singletonMap("token", token);
//
//        // Call the authentication microservice to validate the token
//        Map<String, Object> validationResult = authServiceFeignClient.validateToken(tokenMap);
//
//    }

//    public Movie addMovie(Movie movie) {
//        return movieRepo.save(movie);
//    }
//
//    public Optional<Movie> getAllMoviesForAuthenticatedUser() {
//        // Get the email of the authenticated user
//        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
//
//        // Fetch movies based on the user (you might have a User entity linked to Movie entity)
//        // Assuming there's a method like findByUserEmail in your MovieRepo
//        return movieRepo.findByTitle(userEmail);
//    }

}
