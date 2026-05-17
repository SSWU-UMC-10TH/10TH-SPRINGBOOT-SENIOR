package com.likelion.umc10th.domain.review.repository;

import com.likelion.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT r FROM Review r WHERE r.store.id = :storeId",
            countQuery = "SELECT count(r) FROM Review r WHERE r.store.id = :storeId")
    Page<Review> findAllByStoreId(@Param("storeId") Integer storeId, Pageable pageable);

    // 1. ID 순 커서 기반
    @Query("SELECT r FROM Review r JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId AND (:lastId IS NULL OR r.id < :lastId) " +
            "ORDER BY r.id DESC")
    Slice<Review> findAllByMemberIdAndIdLessThanOrderByIdDesc(
            @Param("memberId") Long memberId,
            @Param("lastId") Long lastId,
            Pageable pageable);

    // 2. 별점 순 커서 기반
    @Query("SELECT r FROM Review r JOIN FETCH r.store s " +
            "WHERE r.member.id = :memberId AND " +
            "(:lastStar IS NULL OR (r.star < :lastStar OR (r.star = :lastStar AND r.id < :lastId))) " +
            "ORDER BY r.star DESC, r.id DESC")
    Slice<Review> findAllByMemberIdOrderByStarDescIdDesc(
            @Param("memberId") Long memberId,
            @Param("lastStar") BigDecimal lastStar,
            @Param("lastId") Long lastId,
            Pageable pageable);


}

