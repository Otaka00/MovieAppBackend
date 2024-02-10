package com.example.MoviesSVC.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
@FeignClient(name = "authentication-service", url = "${authentication-service.url}")
@Component
public interface AuthenticationServiceFeignClient {
    @GetMapping("/api/v1/validate")
    ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authorizationHeader);

}
