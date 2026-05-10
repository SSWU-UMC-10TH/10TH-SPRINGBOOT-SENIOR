package com.likelion.umc10th.domain.review.controller;

import com.likelion.umc10th.domain.review.dto.ReviewReqDTO;
import com.likelion.umc10th.domain.review.dto.ReviewResDTO;
import com.likelion.umc10th.domain.review.service.ReviewService;
import com.likelion.umc10th.global.apiPayload.ApiResponse;
import com.likelion.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    // 과제 1 - 리뷰 작성
    @PostMapping("/{memberId}/stores/{storeId}")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "storeId") Integer storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
    ) {
        ReviewResDTO.CreateReviewResultDTO result = reviewService.createReview(memberId, storeId, request);
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    @PostMapping("/reviews/mine")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.MyReviewListRequestDTO request
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, reviewService.getMyReviewList(request));
    }
}
