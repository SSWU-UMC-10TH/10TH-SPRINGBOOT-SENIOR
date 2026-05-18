package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;
    private final MissionRepository missionRepository;
    // 진행 중 / 완료 미션 조회
//    @GetMapping("/missions")
//    public ApiResponse<Page<MissionResDto.MyMissionDto>> getMissions(
//            @RequestHeader(value = "Authorization", required = false) String authorization,
//            @RequestParam("is_progressed") Boolean isProgressed,
//            @RequestParam("is_finished") Boolean isFinished,
//            @PageableDefault(size = 10) Pageable pageable
//    ) {
//        return ApiResponse.onSuccess(
//                MissionSuccessCode.MISSION_LIST_OK,
//                missionService.getMyMissions(authorization, isFinished, pageable)
//        );
//    }
    //7주차과제-request body 로 사용자 아이디 + 오프셋 기반 페이지네이션
    @PostMapping("/missions")
    public ApiResponse<Page<MissionResDto.MyMissionDto>> getMissions(
            @Valid @RequestBody MissionReqDto.MissionReqDto1 request,
            @RequestParam("is_finished") Boolean isFinished,
            @PageableDefault(size = 10) Pageable pageable
    ) {

        return ApiResponse.onSuccess(
                MissionSuccessCode.MISSION_LIST_OK,
                missionService.getMyMissions(
                        request.userId(),
                        isFinished,
                        pageable
                )
        );
    }

    @Transactional
    public void completeMission(Long missionId) {

        Long memberId = 1L;

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        // TODO: 성공 처리
    }
}
