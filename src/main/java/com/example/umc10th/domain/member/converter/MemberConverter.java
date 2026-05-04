package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDto;
import com.example.umc10th.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDto.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDto.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .phoneNumber(member.getPhoneNum())
                .build();
    }
}
