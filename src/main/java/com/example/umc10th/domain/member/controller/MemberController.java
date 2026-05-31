package com.example.umc10th.domain.member.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.example.umc10th.domain.member.dto.MemberReqDto;
import com.example.umc10th.domain.member.dto.MemberResDto;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.domain.mission.service.MissionService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDto.GetInfo> getInfo(
            @RequestBody MemberReqDto.GetInfo dto
            ){
        BaseSuccessCode code= GeneralSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    @GetMapping("/home")
    public MemberResDto.HomeResDto getHome(
            @RequestParam String region,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return memberService.getHome(region, pageable);
    }


//    @PostMapping("/signup")
//    public ApiResponse<MemberResDto.SignUpResultDto> signUp(
//            @RequestBody MemberReqDto.SignUpDto request
//    ) {
//        return ApiResponse.onSuccess(
//                MemberSuccessCode.OK,
//                memberService.signUp(request)
//        );
//    }
    @PostMapping("/signup")
    public ApiResponse<MemberResDto.SignUpResultDto> signUp(@RequestBody MemberReqDto.SignUpDto request) {

        return ApiResponse.onSuccess(
                GeneralSuccessCode.CREATED,
                memberService.signUp(request)
        );
    }


}
