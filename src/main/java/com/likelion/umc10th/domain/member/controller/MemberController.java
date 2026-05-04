package com.likelion.umc10th.domain.member.controller;

import com.likelion.umc10th.domain.member.dto.MemberReqDTO;
import com.likelion.umc10th.domain.member.dto.MemberResDTO;
import com.likelion.umc10th.domain.mission.dto.MissionResDTO;
import com.likelion.umc10th.domain.review.dto.ReviewResDTO;
import com.likelion.umc10th.global.apiPayload.ApiResponse;
import com.likelion.umc10th.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    @PostMapping("auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDTO request
    ) {

        // 추후 서비스단 제작시 바꿀 코드들
        MemberResDTO.SignUpResultDTO test = MemberResDTO.SignUpResultDTO.builder()
                .memberId(1L) // 테스트용 유저
                .createdAt(java.time.LocalDateTime.now())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, test);
    }

    @GetMapping("users/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @GetMapping("users/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @GetMapping("users/me/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHomeView() {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }
}
