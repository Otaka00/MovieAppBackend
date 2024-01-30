package com.Sumerge.MovieApp.repository;

import com.Sumerge.MovieApp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {
}
