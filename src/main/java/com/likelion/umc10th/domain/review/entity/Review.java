package com.likelion.umc10th.domain.review.entity;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.store.entity.Store;
import com.likelion.umc10th.global.config.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reviews")
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false,columnDefinition = "TEXT")
    private String content;

    @Column(name = "star")
    private BigDecimal star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
