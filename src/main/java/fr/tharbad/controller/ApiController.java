package fr.tharbad.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class ApiController {
    
    @GetMapping("/public")
    public String publicApi() {
        return "{\"message\": \"PUBLIC API!\"}";
    }
    
    @GetMapping("/user")
    public String userApi() {
        return "{\"message\": \"USER API!\"}";
    }

    @GetMapping("/admin")
    public String adminApi() {
        return "{\"message\": \"ADMIN API!\"}";
    }
}
