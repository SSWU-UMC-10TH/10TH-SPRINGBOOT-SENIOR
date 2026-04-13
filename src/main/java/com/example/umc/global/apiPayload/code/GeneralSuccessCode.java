package com.example.umc.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청이 성공했습니다."),

    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "데이터가 성공적으로 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "COMMON202_1",
            "요청이 성공적으로 받아들여졌습니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
            "COMMON204_1",
            "응답 본문에 내용이 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}