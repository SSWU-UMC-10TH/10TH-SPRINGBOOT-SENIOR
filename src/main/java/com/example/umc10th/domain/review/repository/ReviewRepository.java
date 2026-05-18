package com.example.umc10th.domain.review.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByMemberIdAndStoreId(Long memberId, Long storeId);
    Boolean existsByMemberAndStore(Member member, Store store);
    Slice<Review> findByMemberIdAndIdLessThanOrderByIdDesc(
            Long memberId,
            Long lastId,
            Pageable pageable
    );
    @Query("""
    select r
    from Review r
    where r.member.id = :memberId
    and (
        r.star < :lastStar
        or (r.star = :lastStar and r.id < :lastId)
    )
    order by r.star desc, r.id desc
""")
    Slice<Review> findReviewsByStarCursor(
            Long memberId,
            Integer lastStar,
            Long lastId,
            Pageable pageable
    );
    Slice<Review> findByMemberIdOrderByIdDesc(
            Long memberId,
            Pageable pageable
    );

    Slice<Review> findByMemberIdOrderByStarDescIdDesc(
            Long memberId,
            Pageable pageable
    );
}