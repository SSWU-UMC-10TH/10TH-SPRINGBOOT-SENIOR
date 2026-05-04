package com.example.umc.domain.mission.dto;

import java.util.List;

public record MyMissionListResponse(
        List<MyMissionResponse> missions,
        Long nextCursor,
        Boolean hasNext
) {
}