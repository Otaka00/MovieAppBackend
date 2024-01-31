package com.Sumerge.MovieApp.service;

import com.Sumerge.MovieApp.model.Movie;
import com.Sumerge.MovieApp.repository.MovieRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private ModelMapper mapper;

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

}
