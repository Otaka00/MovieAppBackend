package com.example.MoviesSVC;

import com.example.MoviesSVC.dao.MovieDAO;
import com.example.MoviesSVC.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.MoviesSVC")
public class MoviesSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesSvcApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner commandLineRunner(MovieDAO movieDAO){
//		return runner-> {
//			createMovie(movieDAO);
//		};
//	}
//
//	private void createMovie(MovieDAO movieDAO) {
//		System.out.println("Creating new movie object ...");
//
//		Movie movie = new Movie(6L, "kareem", "1414", "kareem", "1414");
//
//		System.out.println("Saving the user ...");
//		movieDAO.save(movie);
//
//		System.out.println("Saved the user with generated id = " + movie.getId());
//	}
}
