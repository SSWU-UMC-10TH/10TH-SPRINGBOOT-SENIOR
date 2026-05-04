package com.example.umc.domain.mission.dto;

import java.time.LocalDateTime;

public record HomeMissionResponse(
        Long userMissionId,
        Long missionId,
        String storeName,
        String foodCategoryName,
        String missionName,
        String missionType,
        LocalDateTime deadline,
        Integer rewardPoint
) {
}