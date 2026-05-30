package com.likelion.umc10th.domain.member.exception.code;

import com.likelion.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MEMBER404_1",
            "존재하지 않는 멤버입니다."),


    DUPLICATE_USERNAME(HttpStatus.CONFLICT,
            "MEMBER409_1",
            "이미 존재하는 아이디 입니다."),

    INVALID_PASSWORD(HttpStatus.CONFLICT,
            "MEMBER404_2",
            "비밀번호가 틀렸습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
