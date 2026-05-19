package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.HomeMissionListResponse;
import com.example.umc.domain.mission.dto.InProgressMissionListResponse;
import com.example.umc.domain.mission.dto.InProgressMissionRequest;
import com.example.umc.domain.mission.dto.MyMissionListResponse;
import com.example.umc.domain.mission.service.MissionService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/my")
    public ApiResponse<MyMissionListResponse> getMyMissions(
            @RequestParam Long userId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getMyMissions(userId, cursor, size)
        );
    }

    @GetMapping
    public ApiResponse<HomeMissionListResponse> getHomeMissions(
            @RequestParam Long regionId,
            @RequestParam(required = false) Long cursor,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(
                GeneralSuccessCode.OK,
                missionService.getHomeMissions(regionId, cursor, size)
        );
    }

    @PatchMapping("/{missionId}")
    public ApiResponse<Void> completeMission(
            @PathVariable Long missionId,
            @RequestParam Long userId
    ) {
        missionService.completeMission(userId, missionId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK,null);
    }

    @PostMapping("/in-progress")
    public ApiResponse<InProgressMissionListResponse> getInProgressMissions(
            @RequestBody @Valid InProgressMissionRequest req
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, missionService.getInProgressMissions(req));
    }
}