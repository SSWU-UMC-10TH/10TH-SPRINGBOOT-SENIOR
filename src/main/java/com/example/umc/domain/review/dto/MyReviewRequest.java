package com.example.umc.domain.review.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record MyReviewRequest(
        @NotNull(message = "사용자 ID는 필수입니다.")
        Long userId,

        Long cursorId,

        Integer cursorStar,

        @NotNull(message = "페이지 크기는 필수입니다.")
        @Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다.")
        Integer size,

        @NotNull(message = "정렬 기준은 필수입니다.")
        MyReviewSortType sortType
) {
}