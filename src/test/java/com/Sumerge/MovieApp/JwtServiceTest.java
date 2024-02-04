//package com.Sumerge.MovieApp;
//import com.Sumerge.MovieApp.entity.User;
//import com.Sumerge.MovieApp.service.JwtService;
//import io.jsonwebtoken.Claims;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.util.ReflectionTestUtils;
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//@TestConfiguration
//@SpringBootTest
//
//class JwtServiceTest {
//
//
//    @Autowired
//    private JwtService jwtService;
//    UserDetails user = User.builder()
//            .email("test@gmail.com")
//            .pass("password")
//            .build();
//
//    @BeforeEach
//    public void setUp() {
//        ReflectionTestUtils.setField(jwtService, "secretKey", "90e78a5375b0cc6d2a0518cb1810416772953847a5287487b37b63d378e3158f");
//        ReflectionTestUtils.setField(jwtService, "jwtExpiration", "259200000");
//    }
//
//    @Test
//    void extractClaim_ValidToken_ReturnsClaim() {
//        String token = generateValidToken();
//        String subject = jwtService.extractUserName(token);
//        assertEquals("test@gmail.com", subject);
//    }
//
//    @Test
//    void generateToken_ValidUser_ReturnsToken() {
//        UserDetails userDetails = user;
//        String token = jwtService.generateToken(userDetails);
//        assertNotNull(token);
//    }
//
//    @Test
//    void isTokenValid_ValidTokenAndUserDetails_ReturnsTrue() {
//        UserDetails userDetails = user;
//        String token = jwtService.generateToken(userDetails);
//        assertTrue(jwtService.isTokenValid(token, userDetails));
//    }
//
//    private String generateValidToken() {
//        Map<String, Object> claims = new HashMap<>();
//        return jwtService.generateToken(claims,
//                User.builder()
//                        .email("test@gmail.com")
//                        .pass("password")
//                        .build());
//    }
//}
