package com.example.umc.domain.terms.entity;

import com.example.demo.global.entity.BaseEntity;
import com.example.umc.domain.terms.enums.TermsType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "terms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "terms_id")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Lob
    @Column(name = "context", nullable = false, columnDefinition = "TEXT")
    private String context;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TermsType type;
}
