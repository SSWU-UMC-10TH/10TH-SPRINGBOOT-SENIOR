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
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "terms", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<UserTerms> userTermsList = new ArrayList<>();
}