package com.example.umc10th.domain.member.entity;

import com.example.umc10th.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import com.example.umc10th.domain.member.enums.MissionStatus;
import com.example.umc10th.domain.mission.entity.Mission;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'IN_PROGRESS'")
    private MissionStatus status;

    private Boolean isFinished;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    public void updateStatusToComplete() {
        this.status = MissionStatus.COMPLETED;
        this.isFinished = true; // 관련 필드들을 함께 변경하여 데이터 일관성 유지
    }
}