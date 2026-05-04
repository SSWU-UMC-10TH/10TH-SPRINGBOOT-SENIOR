package com.example.umc10th.domain.review.dto;


import java.util.List;

public class ReviewResDto {

    public record CreateReviewDto(
            Long missionId,
            Integer rating,
            String content,
            List<String> photos
    ) {}
}