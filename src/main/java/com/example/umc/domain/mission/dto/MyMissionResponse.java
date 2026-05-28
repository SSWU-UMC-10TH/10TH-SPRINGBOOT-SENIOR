package com.example.umc.domain.mission.dto;

import com.example.umc.domain.mission.enums.MissionStatus;

import java.time.LocalDateTime;

public record MyMissionResponse(
        Long userMissionId,
        Long missionId,
        String storeName,
        String storeCategory,
        String missionName,
        String missionType,
        LocalDateTime deadline,
        MissionStatus status
) {
}