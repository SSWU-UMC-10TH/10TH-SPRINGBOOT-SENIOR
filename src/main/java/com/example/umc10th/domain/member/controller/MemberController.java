package com.example.umc10th.domain.member.controller;

import ch.qos.logback.core.model.processor.PhaseIndicator;
import com.example.umc10th.domain.member.dto.MemberReqDto;
import com.example.umc10th.domain.member.dto.MemberResDto;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MemberController {

    private final MemberService memberService;

    //mypage
    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDto.GetInfo> getInfo(
            @RequestBody MemberReqDto.GetInfo dto
            ){
        BaseSuccessCode code= MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

}
