package com.example.umc.domain.review.repository;

import com.example.umc.domain.review.dto.MyReviewResponse;
import com.example.umc.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
        SELECT new com.example.umc.domain.review.dto.MyReviewResponse(
            r.id,
            s.id,
            s.name,
            r.star,
            r.content
        )
        FROM Review r
        JOIN r.store s
        WHERE r.user.id = :userId
          AND r.id > :cursorId
        ORDER BY r.id ASC
    """)
    List<MyReviewResponse> findMyReviewsOrderByIdAsc(
            @Param("userId") Long userId,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );

    @Query("""
        SELECT new com.example.umc.domain.review.dto.MyReviewResponse(
            r.id,
            s.id,
            s.name,
            r.star,
            r.content
        )
        FROM Review r
        JOIN r.store s
        WHERE r.user.id = :userId
          AND (
                r.star < :cursorStar
                OR (r.star = :cursorStar AND r.id > :cursorId)
              )
        ORDER BY r.star DESC, r.id ASC
    """)
    List<MyReviewResponse> findMyReviewsOrderByStarDesc(
            @Param("userId") Long userId,
            @Param("cursorStar") Integer cursorStar,
            @Param("cursorId") Long cursorId,
            Pageable pageable
    );
}