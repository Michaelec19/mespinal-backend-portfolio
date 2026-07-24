package com.michaelespinal.portfolio_api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;
    private final String SECRET = "mySuperSecretKeyForTesting1234567890";

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
        ReflectionTestUtils.setField(jwtUtil, "secretKey", SECRET);
    }

    @Test
    void generateToken_ReturnsValidToken() {
        String token = jwtUtil.generateToken("testUser");
        
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void isValidToken_WithValidToken_ReturnsTrue() {
        String token = jwtUtil.generateToken("testUser");
        
        assertTrue(jwtUtil.isValidToken(token));
    }

    @Test
    void isValidToken_WithInvalidToken_ReturnsFalse() {
        assertFalse(jwtUtil.isValidToken("an.invalid.token"));
    }

    @Test
    void extractUsername_ReturnsCorrectUsername() {
        String expectedUser = "testUser";
        String token = jwtUtil.generateToken(expectedUser);
        
        String actualUser = jwtUtil.extractUsername(token);
        
        assertEquals(expectedUser, actualUser);
    }
}