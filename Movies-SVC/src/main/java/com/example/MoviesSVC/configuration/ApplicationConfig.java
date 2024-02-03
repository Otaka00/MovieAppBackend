package com.example.MoviesSVC.configuration;
import com.example.MoviesSVC.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Bean
    public MovieService movieBean() {
        return new MovieService();
    }

    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }

}
