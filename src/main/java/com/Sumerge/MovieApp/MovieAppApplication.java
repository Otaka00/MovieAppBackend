package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.dao.UserDAO;
import com.Sumerge.MovieApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MovieAppApplication {
	public static void main(String[] args)  {
		SpringApplication.run(MovieAppApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(UserDAO userDAO){
//		return runner-> {
//			createUser(userDAO);
//		};
//	}
//
//	private void createUser(UserDAO userDAO) {
//		System.out.println("Creating new user object ...");
//
//		User user1 = new User("kareem", "1414");
//
//		System.out.println("Saving the user ...");
//		userDAO.save(user1);
//
//		System.out.println("Saved the user with generated id = " + user1.getId());
//	}
}

