package com.likelion.umc10th.domain.member.controller;

import com.likelion.umc10th.domain.member.dto.MemberReqDTO;
import com.likelion.umc10th.domain.member.dto.MemberResDTO;
import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.service.MemberService;
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

    private final MemberService memberService;

    @PostMapping("auth/signup")
    public ApiResponse<MemberResDTO.SignUpResultDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDTO request
    ) {
        Member member = memberService.joinMember(request);

        MemberResDTO.SignUpResultDTO result = MemberResDTO.SignUpResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();

        return ApiResponse.onSuccess(GeneralSuccessCode.CREATED, result);
    }

    @GetMapping("users/me/reviews")
    public ApiResponse<ReviewResDTO.MyReviewListDTO> getMyReviews(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    @GetMapping("members/me/missions")
    public ApiResponse<MissionResDTO.MyMissionListDTO> getMyMissions(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
    }

    // 과제 4 - 홈 화면
    @GetMapping("members/me/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHomeView(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "regionId") Integer regionId,
            @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        MemberResDTO.HomeViewDTO result = memberService.getHomeView(memberId, regionId, page);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }

    // 과제 3 - 마이 페이지
    @GetMapping("members/mypage")
    public ApiResponse<MemberResDTO.MyPageDTO> getMyPage(
            @RequestParam(name = "memberId") Long memberId
    ) {
        MemberResDTO.MyPageDTO result = memberService.getMyPageView(memberId);
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, result);
    }
}
