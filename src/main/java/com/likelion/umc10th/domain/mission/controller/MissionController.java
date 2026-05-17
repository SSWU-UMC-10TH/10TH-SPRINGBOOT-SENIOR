package com.likelion.umc10th.domain.mission.controller;

import com.likelion.umc10th.domain.mission.dto.MissionReqDTO;
import com.likelion.umc10th.domain.mission.dto.MissionResDTO;
import com.likelion.umc10th.domain.mission.service.MissionService;
import com.likelion.umc10th.global.apiPayload.ApiResponse;
import com.likelion.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 미션 상태 변경
    @PatchMapping("/missions/{userMissionId}")
    public ApiResponse<MissionResDTO.MissionStatusResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId,
            @RequestBody @Valid MissionReqDTO.MissionStatusDTO request
            ) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 과제 2 - 내가 진행중 / 완료한 미션 모아서 보기
    @PostMapping("/missions/mine")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestBody MissionReqDTO.MemberIdDTO request,
            @RequestParam(name = "status") String status, // "challenging" 또는 "complete"로 받기!
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Boolean isCompleted = status.equalsIgnoreCase("complete");
        MissionResDTO.MyMissionListDTO result = missionService.getMyMissionList(
                request.getMemberId(), isCompleted, page);

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }


}
