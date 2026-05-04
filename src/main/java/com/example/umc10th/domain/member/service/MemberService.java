package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDto;
import com.example.umc10th.domain.member.dto.MemberResDto;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public MemberResDto.GetInfo getInfo(MemberReqDto.GetInfo dto) {

    Long memberId = dto.id();
    Member member=memberRepository.findById(memberId)
            .orElseThrow(() -> new MemberException((MemberErrorCode.MEMBER_NOT_FOUND)));

    return MemberConverter.toGetInfo(member);
    }
}
