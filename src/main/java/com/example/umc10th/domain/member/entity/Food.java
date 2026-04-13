package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.FoodCategory;
import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.global.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private FoodCategory name;
}
