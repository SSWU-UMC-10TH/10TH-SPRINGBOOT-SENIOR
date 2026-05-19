package com.example.umc.domain.mission.dto;

import java.util.List;

public record InProgressMissionListResponse(
        List<InProgressMissionResponse> missions,
        Integer page,
        Integer size,
        Boolean hasNext
) {
}