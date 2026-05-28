package com.example.umc.domain.review.dto;

public record MyReviewResponse(
        Long reviewId,
        Long storeId,
        String storeName,
        Integer star,
        String content
) {
}