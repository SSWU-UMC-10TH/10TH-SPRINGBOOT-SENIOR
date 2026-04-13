package com.likelion.umc10th.domain.mission.controller;

import com.likelion.umc10th.domain.mission.dto.MissionReqDTO;
import com.likelion.umc10th.domain.mission.dto.MissionResDTO;
import com.likelion.umc10th.global.apiPayload.ApiResponse;
import com.likelion.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MissionController {

    @PatchMapping("/missions/{userMissionId}")
    public ApiResponse<MissionResDTO.MissionStatusResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId,
            @RequestBody @Valid MissionReqDTO.MissionStatusDTO request
            ) {

        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
