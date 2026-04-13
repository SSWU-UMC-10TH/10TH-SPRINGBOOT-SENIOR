package com.example.umc10th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.global.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class QnaImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String meta;

    @Column(columnDefinition = "TEXT") //4주차 피드백 수정-2
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "qna_id")
    private Qna qna;
}