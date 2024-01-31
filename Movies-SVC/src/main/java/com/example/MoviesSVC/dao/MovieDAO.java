package com.example.MoviesSVC.dao;

import com.example.MoviesSVC.model.Movie;

public interface MovieDAO {
    void save(Movie movie);
    Movie findById(long id);
}
