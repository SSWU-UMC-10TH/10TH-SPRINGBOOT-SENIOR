package com.example.umc.domain.auth.dto;

public record SignupResponse(
        Long userId,
        Long authId,
        String email,
        String nickname
) {
}