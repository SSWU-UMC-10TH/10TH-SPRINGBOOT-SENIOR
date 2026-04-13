package com.likelion.umc10th.domain.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignUpResultDTO (
        Long memberId,
        LocalDateTime createdAt
    ) {}

    @Builder
    public record HomeViewDTO(
            String currentRegion,
            Integer totalPoints,
            Integer completedMissions,
            Integer targetMissions,
            List<HomeMissionSummaryDTO> myMissions
    ){}

    @Builder
    public record HomeMissionSummaryDTO(
            Long userMissionId,
            String storeName,
            String condition,
            Integer point,
            String deadlineDDay,
            String status
    ){}
}
