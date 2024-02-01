package com.example.MoviesSVC.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;
@FeignClient(name = "authentication-service", url = "http://localhost:8080")
public interface AuthenticationServiceFeignClient {
    @PostMapping("/api/v1/auth/validate-token")
    Map<String, Object> validateToken(@RequestBody Map<String, String> tokenMap);
}
