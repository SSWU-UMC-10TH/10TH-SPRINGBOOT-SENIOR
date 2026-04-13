package com.likelion.umc10th.domain.review.controller;

import com.likelion.umc10th.domain.review.dto.ReviewReqDTO;
import com.likelion.umc10th.domain.review.dto.ReviewResDTO;
import com.likelion.umc10th.global.apiPayload.ApiResponse;
import com.likelion.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    @PostMapping("/")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @RequestBody @Valid ReviewReqDTO.CreateReviewDTO request
            ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, null);
    }
}
