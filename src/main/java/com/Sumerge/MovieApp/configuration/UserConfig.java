package com.Sumerge.MovieApp.configuration;
import org.modelmapper.ModelMapper;
import com.Sumerge.MovieApp.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserService userBean() {
        return new UserService();
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}
