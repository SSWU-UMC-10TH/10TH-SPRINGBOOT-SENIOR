package com.example.umc.domain.review.dto;

import java.util.List;

public record MyReviewListResponse(
        List<MyReviewResponse> reviews,
        Long nextCursorId,
        Integer nextCursorStar,
        Boolean hasNext
) {
}