package com.example.MoviesSVC.repository;
import com.example.MoviesSVC.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {
    List<Movie> findByUserEmail(String userEmail);
}
