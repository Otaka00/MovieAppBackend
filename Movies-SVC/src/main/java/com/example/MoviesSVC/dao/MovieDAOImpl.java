package com.example.MoviesSVC.dao;

import com.example.MoviesSVC.model.Movie;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MovieDAOImpl implements MovieDAO {

    private EntityManager entityManager;

    @Autowired
    public MovieDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Movie movie) {
        entityManager.persist(movie);
    }

    @Override
    public Movie findById(long id) {
        return entityManager.find(Movie.class, id);
    }
}
