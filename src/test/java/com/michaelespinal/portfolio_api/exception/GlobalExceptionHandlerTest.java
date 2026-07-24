package com.michaelespinal.portfolio_api.exception;

import com.michaelespinal.portfolio_api.dto.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleIllegalArgumentException_ReturnsBadRequest() {
        // Arrange
        String errorMessage = "Invalid data provided";
        IllegalArgumentException ex = new IllegalArgumentException(errorMessage);

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleIllegalArgumentException(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Bad Request", response.getBody().getError());
        assertEquals(errorMessage, response.getBody().getMessage());
    }

    @Test
    void handleGlobalException_ReturnsInternalServerError() {
        // Arrange
        Exception ex = new Exception("Catastrophic database error");

        // Act
        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGlobalException(ex);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(500, response.getBody().getStatus());
        assertEquals("Internal Server Error", response.getBody().getError());
        assertEquals("Unexpected Server Error", response.getBody().getMessage());
    }
} 
    
