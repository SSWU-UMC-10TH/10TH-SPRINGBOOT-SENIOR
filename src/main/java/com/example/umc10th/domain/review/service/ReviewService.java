package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    public ReviewResDto.CreateReviewDto createReview(String authorization, ReviewReqDto.CreateReviewDto request) {

        Long memberId = 1L; // 테스트용

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        Mission mission = missionRepository.findById(request.missionId())
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        Store store = mission.getStore();

        boolean exists = reviewRepository.existsByMemberAndStore(member, store);
        if (exists) {
            throw new RuntimeException("이미 리뷰 작성함");
        }

        if (request.rating() < 1 || request.rating() > 5) {
            throw new RuntimeException("별점은 1~5 사이여야 함");
        }

        Review review = Review.builder()
                .member(member)
                .store(store)
                .star(request.rating().floatValue())
                .content(request.content())
                .build();

        Review savedReview = reviewRepository.save(review);

        return new ReviewResDto.CreateReviewDto(
                savedReview.getId(),
                mission.getId(),
                savedReview.getStar().intValue(),
                savedReview.getContent()
        );
    }
}