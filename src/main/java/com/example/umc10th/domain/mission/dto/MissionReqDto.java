package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

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
    public record MissionReqDto1(

            @NotNull(message = "사용자 ID는 필수입니다.")
            Long userId

    ) {
    }
}
