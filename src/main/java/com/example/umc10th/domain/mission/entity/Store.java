package com.example.umc10th.domain.mission.entity;


import com.example.umc10th.domain.member.enums.Address;
import com.example.umc10th.domain.member.enums.FoodCategory;
import com.example.umc10th.global.BaseEntity;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column( length = 50)
    private String lane;

    @Column(length = 20)
    private String phoneNum;

    @Column(length = 50)
    private String detailAddress;

    private FoodCategory category;

    private LocalTime openingHours;

    private LocalTime closingHours;


    @Enumerated(EnumType.STRING) //  Region-> Address Enum 변경
    @Column(nullable = false, length = 15)
    private Address address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Review> reviewList = new ArrayList<>();

    @Column(nullable = false, length = 30)
    private String region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Mission> missionList = new ArrayList<>();
}