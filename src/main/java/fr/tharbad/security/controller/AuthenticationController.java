package fr.tharbad.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.tharbad.security.dto.RegisterDTO;
import fr.tharbad.security.models.Role;
import fr.tharbad.security.models.UserEntity;
import fr.tharbad.security.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    // public AuthenticationController(UserRepository userRepository) {
    //     this.userRepository = userRepository;
    // }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) { 
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Error: Email is already in use!");
        }

        UserEntity user = new UserEntity();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(registerDTO.getPassword());
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }
}
