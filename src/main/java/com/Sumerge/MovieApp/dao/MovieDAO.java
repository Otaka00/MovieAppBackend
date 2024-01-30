package com.Sumerge.MovieApp.dao;

import com.Sumerge.MovieApp.entity.User;
import com.Sumerge.MovieApp.model.Movie;

public interface MovieDAO {
    void save(Movie movie);
    Movie findById(long id);
}
