package com.likelion.umc10th.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {
    @Builder
    public record MyMissionListDTO(
            List<MyMissionDetailDTO> missionList,
            Integer totalPages,
            Long totalElements,
            Boolean isLast
    ) {}

    @Builder
    public record MyMissionDetailDTO(
            Long userMissionId,
            Integer point,
            String storeName,
            String status,
            String condition,
            LocalDate createdAt
    ) {}

    @Builder
    public record MissionStatusResultDTO(
            Long userMissionId,
            String status,      // "COMPLETED"
            LocalDate updatedAt
    ) {}
}
