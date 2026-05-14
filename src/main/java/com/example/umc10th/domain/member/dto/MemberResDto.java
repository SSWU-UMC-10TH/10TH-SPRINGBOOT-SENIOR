package com.example.umc10th.domain.member.dto;

import lombok.Builder;
import org.springframework.data.domain.Page;

public class MemberResDto {
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    public record HomeInfoDto(
            String nickname,
            Integer point,
            Integer ongoingMissionCount,
            Integer completedMissionCount
    ) {}

    public record HomeResDto(
            String regionName,
            int completedCount,
            int totalGoalCount,
            int nextRewardPoint,
            int currentPoint,
            Page<HomeMissionDto> missions
    ) {}
    public record HomeMissionDto(
            Long missionId,
            String storeName,
            String category,
            Long remainingDays,
            String conditionAmount,
            Integer rewardPoint,
            Boolean isParticipating
    ) {}
    public record SignUpResultDto(
            Long memberId,
            String email,
            String name
    ) {}
}
