package com.example.MoviesSVC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class MoviesSvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoviesSvcApplication.class, args);
	}
}
