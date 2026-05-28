package com.example.umc.domain.mission.dto;

import java.util.List;

public record HomeMissionListResponse(
        List<HomeMissionResponse> missions,
        Long nextCursor,
        Boolean hasNext
) {
}