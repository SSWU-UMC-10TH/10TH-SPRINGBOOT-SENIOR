package com.example.umc.domain.mission.dto;

import java.time.LocalDateTime;

public record InProgressMissionResponse(
        Long userMissionId,
        Long missionId,
        String missionName,
        String missionType,
        String storeName,
        LocalDateTime deadline
) {
}