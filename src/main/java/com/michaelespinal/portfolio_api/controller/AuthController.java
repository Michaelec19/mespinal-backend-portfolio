package com.michaelespinal.portfolio_api.controller;

import com.michaelespinal.portfolio_api.dto.LoginRequest;
import com.michaelespinal.portfolio_api.model.User;
import com.michaelespinal.portfolio_api.repository.UserRepository;
import com.michaelespinal.portfolio_api.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.username());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            
            if (passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
                
                String token = jwtUtil.generateToken(user.getUsername());
                
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                return ResponseEntity.ok(response);
            }
        }

        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Credenciales inválidas");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}