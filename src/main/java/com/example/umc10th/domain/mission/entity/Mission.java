package com.example.umc10th.domain.mission.entity;


import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.domain.member.entity.UserMission;
import com.example.umc10th.global.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String missionCondition; //미션 조건(내용)

    private Integer point;
    private LocalDate deadline;
    private Integer rewardPercent;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;


    @OneToMany(mappedBy = "mission", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<UserMission> userMissionList = new ArrayList<>();
}