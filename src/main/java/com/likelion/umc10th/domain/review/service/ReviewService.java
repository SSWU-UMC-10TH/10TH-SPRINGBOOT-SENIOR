package com.likelion.umc10th.domain.review.service;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.repository.MemberRepository;
import com.likelion.umc10th.domain.review.dto.ReviewReqDTO;
import com.likelion.umc10th.domain.review.dto.ReviewResDTO;
import com.likelion.umc10th.domain.review.entity.Review;
import com.likelion.umc10th.domain.review.repository.ReviewRepository;
import com.likelion.umc10th.domain.store.entity.Store;
import com.likelion.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.CreateReviewResultDTO createReview(Long memberId, Integer storeId, ReviewReqDTO.CreateReviewDTO requestDto) {

        // 1. 리뷰를 작성할 가게가 있는지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("해당 가게가 존재하지 않습니다."));

        // 2. 작성자(유저)가 있는지 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 존재하지 않습니다."));

        // 3. Review 객체 생성 및 연관관계 설정
        Review review = Review.builder()
                .content(requestDto.content())
                .star(requestDto.star())
                .store(store)
                .member(member)
                .build();

        Review savedReview = reviewRepository.save(review);

        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(savedReview.getId())
                .createdAt(savedReview.getCreatedAt().toLocalDate())
                .build();
    }

    public Page<Review> getStoreReviews(Integer storeId, Integer page) {
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, 10, Sort.by("createdAt").descending());
        return reviewRepository.findAllByStoreId(storeId, pageable);
    }
}
