package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.CreateReviewRequest;
import com.example.umc.domain.review.dto.MyReviewListResponse;
import com.example.umc.domain.review.dto.MyReviewRequest;
import com.example.umc.domain.review.dto.MyReviewSortType;
import com.example.umc.domain.review.service.ReviewService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<Void> createReview(@RequestBody @Valid CreateReviewRequest req) {
        reviewService.createReview(req);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }

    @GetMapping("/my")
    public ApiResponse<MyReviewListResponse> getMyReviews(
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(required = false) Integer cursorStar,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "ID_ASC") MyReviewSortType sortType
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,
                reviewService.getMyReviews(
                        userId,
                        cursorId,
                        cursorStar,
                        size,
                        sortType
                )
        );
    }
}