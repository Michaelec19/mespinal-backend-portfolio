package com.michaelespinal.portfolio_api.dto;

public record LoginRequest(
    String username,
    String password
) {}