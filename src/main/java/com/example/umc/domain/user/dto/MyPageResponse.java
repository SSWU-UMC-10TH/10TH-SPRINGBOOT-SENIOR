package com.example.umc.domain.user.dto;

public record MyPageResponse(
        Long userId,
        String nickname,
        String email,
        String phoneNumber,
        Boolean phoneVerified,
        Integer point
) {
}