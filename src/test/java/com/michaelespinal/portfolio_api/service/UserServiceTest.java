package com.michaelespinal.portfolio_api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.michaelespinal.portfolio_api.model.User;
import com.michaelespinal.portfolio_api.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void createUser_Success() {
        User u = new User();
        u.setUsername("admin");
        u.setEmail("admin@test.com");
        u.setPassword("password123");

        when(passwordEncoder.encode("password123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(u);

        User saved = userService.createUser(u);
        
        assertEquals("hashedPassword", saved.getPassword());
        verify(passwordEncoder, times(1)).encode("password123");
    }

    @Test
    void createUser_ShortPassword_ThrowsException() {
        User u = new User();
        u.setUsername("admin");
        u.setEmail("admin@test.com");
        u.setPassword("123");

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(u));
    }

    @Test
    void getUserById_ReturnsUser() {
        User u = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(u));

        assertTrue(userService.getUserById(1L).isPresent());
    }
}