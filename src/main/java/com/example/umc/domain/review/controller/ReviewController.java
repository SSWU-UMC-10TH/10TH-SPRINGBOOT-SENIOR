package com.example.umc.domain.review.controller;

import com.example.umc.domain.review.dto.CreateReviewRequest;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @PostMapping
    public ApiResponse<Void> createReview(@RequestBody @Valid CreateReviewRequest req) {
        return null;
    }
}