package com.example.umc.domain.review.service;

import com.example.umc.domain.review.dto.*;
import com.example.umc.domain.review.entity.Review;
import com.example.umc.domain.review.repository.ReviewRepository;
import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.store.repository.StoreRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional(readOnly = true)
    public MyReviewListResponse getMyReviews(
            Long userId,
            Long cursorId,
            Integer cursorStar,
            Integer size,
            MyReviewSortType sortType
    ) {
        int fetchSize = size + 1;

        List<MyReviewResponse> result = switch (sortType) {
            case ID_ASC -> reviewRepository.findMyReviewsOrderByIdAsc(
                    userId,
                    cursorId == null ? 0L : cursorId,
                    PageRequest.of(0, fetchSize)
            );

            case STAR_DESC -> reviewRepository.findMyReviewsOrderByStarDesc(
                    userId,
                    cursorStar == null ? 6 : cursorStar,
                    cursorId == null ? 0L : cursorId,
                    PageRequest.of(0, fetchSize)
            );
        };

        boolean hasNext = result.size() > size;

        if (hasNext) {
            result = result.subList(0, size);
        }

        MyReviewResponse last = result.isEmpty() ? null : result.get(result.size() - 1);

        return new MyReviewListResponse(
                result,
                hasNext && last != null ? last.reviewId() : null,
                hasNext && last != null ? last.star() : null,
                hasNext
        );
    }
}
