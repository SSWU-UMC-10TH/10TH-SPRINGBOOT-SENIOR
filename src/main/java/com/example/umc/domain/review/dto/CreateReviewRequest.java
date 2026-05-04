package com.example.umc.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateReviewRequest(
        @NotNull(message = "가게 ID는 필수입니다.")
        Long storeId,

        @NotNull(message = "사용자 ID는 필수입니다.")
        Long userId,

        @NotNull(message = "별점은 필수입니다.")
        @Min(value = 1, message = "별점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "별점은 5점 이하여야 합니다.")
        Integer star,

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        String content,

        List<String> images
) {
}