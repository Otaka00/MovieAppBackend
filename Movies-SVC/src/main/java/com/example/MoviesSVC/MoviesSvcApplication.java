package com.example.MoviesSVC;

import com.example.MoviesSVC.model.Movie;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MoviesSvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesSvcApplication.class, args);
	}

}
