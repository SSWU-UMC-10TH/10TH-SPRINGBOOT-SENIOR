package com.example.umc.domain.review.entity;

import com.example.umc.domain.store.entity.Store;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "star", nullable = false)
    private Integer star;

    @Lob
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    public static Review create(Store store, User user, Integer star, String content) {
        validateStore(store);
        validateUser(user);
        validateStar(star);
        validateContent(content);

        Review review = new Review();
        review.store = store;
        review.user = user;
        review.star = star;
        review.content = content;

        return review;
    }

    private static void validateStore(Store store) {
        if (store == null) {
            throw new IllegalArgumentException("가게 정보는 필수입니다.");
        }
    }

    private static void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("사용자 정보는 필수입니다.");
        }
    }

    private static void validateStar(Integer star) {
        if (star == null || star < 1 || star > 5) {
            throw new IllegalArgumentException("별점은 1점 이상 5점 이하여야 합니다.");
        }
    }

    private static void validateContent(String content) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("리뷰 내용은 필수입니다.");
        }
    }
}