package com.Sumerge.MovieApp.model;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private Date releaseDate;
    private String description;

}
