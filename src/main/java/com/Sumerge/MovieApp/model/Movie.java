package com.Sumerge.MovieApp.model;

import com.Sumerge.MovieApp.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    private Long id;
    private String title;
    private double voteAverage;
    private String releaseDate;
    private String posterPath;

    @ManyToMany(fetch  = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> likedBy = new HashSet<>();

    @ManyToMany(fetch  = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "watchlist",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> watchlistedBy = new HashSet<>();
}
