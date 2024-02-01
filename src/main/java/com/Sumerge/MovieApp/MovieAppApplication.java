package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.dao.UserDAO;
import com.Sumerge.MovieApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
public class MovieAppApplication {
	public static void main(String[] args)  {
		SpringApplication.run(MovieAppApplication.class, args);
	}

}

