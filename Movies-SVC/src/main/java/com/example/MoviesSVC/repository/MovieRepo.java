package com.example.MoviesSVC.repository;
import com.example.MoviesSVC.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
