package com.example.umc.domain.auth.dto;

public record LoginRequest(
        String email,
        String password
) {
}
