package com.example.umc10th.domain.review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class ReviewReqDto {

    public record CreateReviewDto(
            Long reviewId,
            Long missionId,
            Integer rating,
            String content,
            List<String> photos
    ) {}
}