package com.example.umc10th.domain.member.dto;

import lombok.Builder;

public class MemberResDto {
    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    public record HomeInfoDto(
            String nickname,
            Integer point,
            Integer ongoingMissionCount,
            Integer completedMissionCount
    ) {}

    public record SignUpResultDto(
            Long memberId,
            String email,
            String name
    ) {}
}
