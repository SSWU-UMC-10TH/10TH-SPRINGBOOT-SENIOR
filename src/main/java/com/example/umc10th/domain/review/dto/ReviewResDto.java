package com.example.umc10th.domain.review.dto;


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
}