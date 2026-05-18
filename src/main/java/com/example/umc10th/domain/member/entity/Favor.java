package com.example.umc10th.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.global.BaseEntity;

@Entity
@Getter
@Builder
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Favor extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean korean;
    private Boolean chinese;
    private Boolean japanese;
    private Boolean western;
    private Boolean snack;
    private Boolean grilledMeat;
    private Boolean sushi;
    private Boolean lateNight;
    private Boolean fastFood;
    private Boolean dessert;
    private Boolean asianFood;


    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

}