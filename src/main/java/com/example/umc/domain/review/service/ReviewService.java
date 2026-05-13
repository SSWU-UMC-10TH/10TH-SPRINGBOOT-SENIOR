package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.CreateReviewRequest;
import com.example.umc.domain.review.dto.CreateReviewResponse;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    @Transactional
    public CreateReviewResponse createReview(CreateReviewRequest req) {
        Store store = storeRepository.findById(req.storeId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Review review = Review.create(
                store,
                user,
                req.star(),
                req.content()
        );

        Review savedReview = reviewRepository.save(review);

        return new CreateReviewResponse(savedReview.getId());
    }
}
