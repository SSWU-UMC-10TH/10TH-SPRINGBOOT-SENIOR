package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ApiResponse<Slice<ReviewResDto.MyReviewDto>> getMyReviews(
            @RequestParam Long userId,
            @RequestParam String sort,
            @RequestParam(required = false) Long lastId,
            @RequestParam(required = false) Integer lastStar,
            @RequestParam(defaultValue = "10") int size
    ) {

        return ApiResponse.onSuccess(
                ReviewSuccessCode.REVIEW_LIST_OK,
                reviewService.getMyReviews(
                        userId,
                        sort,
                        lastId,
                        lastStar,
                        size
                )
        );
    }
}
