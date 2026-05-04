package com.likelion.umc10th.domain.review.repository;

import com.likelion.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT r FROM Review r WHERE r.store.id = :storeId",
            countQuery = "SELECT count(r) FROM Review r WHERE r.store.id = :storeId")
    Page<Review> findAllByStoreId(@Param("storeId") Integer storeId, Pageable pageable);}
