package com.Sumerge.MovieApp;

import com.Sumerge.MovieApp.configuration.UserConfig;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import com.Sumerge.MovieApp.service.UserService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserConfigTest {

    @Test
    void userBeanShouldNotBeNull() {
        // Arrange
        UserConfig userConfig = new UserConfig();

        // Act
        UserService userService = userConfig.userBean();

        // Assert
        assertNotNull(userService);
    }

    @Test
    void modelMapperBeanShouldNotBeNull() {
        // Arrange
        UserConfig userConfig = new UserConfig();

        // Act
        ModelMapper modelMapper = userConfig.modelMapperBean();

        // Assert
        assertNotNull(modelMapper);
    }
}
