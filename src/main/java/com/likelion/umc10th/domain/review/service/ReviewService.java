package com.likelion.umc10th.domain.review.service;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.exception.MemberException;
import com.likelion.umc10th.domain.member.exception.code.MemberErrorCode;
import com.likelion.umc10th.domain.member.repository.MemberRepository;
import com.likelion.umc10th.domain.review.dto.ReviewReqDTO;
import com.likelion.umc10th.domain.review.dto.ReviewResDTO;
import com.likelion.umc10th.domain.review.entity.Review;
import com.likelion.umc10th.domain.review.repository.ReviewRepository;
import com.likelion.umc10th.domain.store.entity.Store;
import com.likelion.umc10th.domain.store.exception.StoreException;
import com.likelion.umc10th.domain.store.exception.code.StoreErrorCode;
import com.likelion.umc10th.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResultDTO createReview(Long memberId, Integer storeId, ReviewReqDTO.CreateReviewDTO requestDto) {

        // 1. 리뷰를 작성할 가게가 있는지 확인
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        // 2. 작성자(유저)가 있는지 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

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

    public ReviewResDTO.MyReviewListDTO getMyReviewList(ReviewReqDTO.MyReviewListRequestDTO request) {

        Pageable pageable = PageRequest.of(0, 10); // 커서 기반은 항상 0번째 페이지부터 limit개만큼 가져옴
        Slice<Review> reviewSlice;

        // 정렬 조건에 따른 분기 처리
        if ("star".equalsIgnoreCase(request.sort())) {
            reviewSlice = reviewRepository.findAllByMemberIdOrderByStarDescIdDesc(
                    request.memberId(), request.lastStar(), request.lastId(), pageable);
        } else {
            reviewSlice = reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(
                    request.memberId(), request.lastId(), pageable);
        }

        // DTO 변환
        List<ReviewResDTO.ReviewDetailDTO> reviewDetailDTOList = reviewSlice.getContent().stream()
                .map(review -> ReviewResDTO.ReviewDetailDTO.builder()
                        .reviewId(review.getId())
                        .storeName(review.getStore().getName())
                        .star(review.getStar())
                        .content(review.getContent())
                        .createdAt(review.getCreatedAt().toLocalDate())
                        .build()
                ).toList();

        // 다음 조회를 위한 커서 값 추출
        Long nextLastId = reviewDetailDTOList.isEmpty() ? null : reviewDetailDTOList.get(reviewDetailDTOList.size() - 1).reviewId();
        BigDecimal nextLastStar = reviewDetailDTOList.isEmpty() ? null : reviewDetailDTOList.get(reviewDetailDTOList.size() - 1).star();

        return ReviewResDTO.MyReviewListDTO.builder()
                .reviewList(reviewDetailDTOList)
                .lastId(nextLastId)
                .lastStar(nextLastStar)
                .hasNext(reviewSlice.hasNext())
                .build();
    }
}
