package com.example.umc10th.domain.mission.dto;

public class MissionReqDto {
    public record MyMissionDto(
            Long missionId,
            Integer rewardPoint,
            String storeName,
            Integer conditionAmount,
            Boolean isSuccess,
            Long reviewId
    ) {
    }
}
