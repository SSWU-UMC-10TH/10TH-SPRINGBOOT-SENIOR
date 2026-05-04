package com.example.umc10th.domain.mission.dto;



import java.util.List;

public class MissionResDto {

    public record MissionListDto(
            List<MissionDto> missions
    ) {}

    public record MissionDto(
            Long missionId,
            String title,
            String content,
            Integer reward,
            Boolean isProgressed,
            Boolean isFinished
    ) {}
    public record MyMissionDto(
            Long missionId,
            Integer rewardPoint,
            String storeName,
            String conditionAmount,
            Boolean isSuccess,
            Long reviewId
    ) {
    }
    public record CompleteMissionDto(
            Long missionId,
            Boolean isFinished,
            String completedAt
    ) {}
}