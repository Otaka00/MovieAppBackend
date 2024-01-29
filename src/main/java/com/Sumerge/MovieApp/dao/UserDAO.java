package com.Sumerge.MovieApp.dao;
import com.Sumerge.MovieApp.entity.User;

public interface UserDAO {
    void save(User user);
    User findById(long id);
}
