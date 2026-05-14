package com.example.umc10th.domain.review.dto;


import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDto {

    public record CreateReviewDto(
            Long reviewId,
            Long missionId,
            Integer rating,
            String content
    ) {}

    public record MyMissionResDto(
            Long missionId,
            Integer rewardPoint,
            String storeName,
            Integer conditionAmount,
            Boolean isSuccess,
            Long reviewId
    ) {
    }

    @Builder
    public record MyReviewDto(
            Long reviewId,
            String storeName,
            Float star,
            String content,
            LocalDate createdAt
    ) {
    }
}