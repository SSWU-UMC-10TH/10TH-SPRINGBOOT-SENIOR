package com.example.umc10th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.global.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Qna extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(length = 10)
    private String type;

    @Column(length = 10)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToOne(mappedBy = "qna", cascade = CascadeType.ALL)
    private QnaReply qnaReply;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL)
    @Builder.Default
    private List<QnaImage> qnaImageList = new ArrayList<>();
}