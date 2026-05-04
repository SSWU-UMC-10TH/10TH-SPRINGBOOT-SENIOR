package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 진행 중 / 완료 미션 조회
    @GetMapping("/missions")
    public ApiResponse<MissionResDto.MissionListDto> getMissions(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("is_progressed") Boolean isProgressed,
            @RequestParam("is_finished") Boolean isFinished
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_OK,
                missionService.getMissions(authorization, isProgressed, isFinished)
        );
    }

    // 미션 성공 누르기
    @PostMapping("/missions/{missionId}/complete")
    public ApiResponse<MissionResDto.CompleteMissionDto> completeMission(
            @PathVariable Long missionId
    ) {
        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_COMPLETE_OK,
                missionService.completeMission(missionId)
        );
    }
}
