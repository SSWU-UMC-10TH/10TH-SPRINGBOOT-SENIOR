package com.likelion.umc10th.domain.review.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

public class ReviewReqDTO {

    @Builder
    public record CreateReviewDTO(
            @NotBlank(message = "리뷰 내용은 필수입니다.")
            String content,

            @NotNull(message = "별점은 필수입니다.")
            @DecimalMin(value = "0.0", message = "별점은 0.0 이상이어야 합니다.")
            @DecimalMax(value = "5.0", message = "별점은 5.0 이하이어야 합니다.")
            BigDecimal star,

            @NotNull(message = "성공한 미션 아이디는 필수입니다.")
            Long userMissionId,

            List<String> reviewPhotoUrls
    ) {}
}
