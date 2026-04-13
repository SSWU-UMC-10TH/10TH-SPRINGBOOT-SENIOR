package com.likelion.umc10th.domain.review.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewDetailDTO(
            Long reviewId,
            String storeName,
            Double star,
            String content,
            LocalDate createdAt
    ) {}

    @Builder
    public record MyReviewListDTO(
            List<ReviewDetailDTO> reviewList,
            Integer totalPages,
            Long totalElements,
            Boolean isLast
    ) {}

    @Builder
    public record CreateReviewResultDTO(
            Long reviewId,
            LocalDate createdAt
    ) {}
}
