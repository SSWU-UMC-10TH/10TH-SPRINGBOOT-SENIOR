package com.likelion.umc10th.global.apiPayload.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "SUCCESS_200",
            "요청에 성공했습니다."),

    CREATED(HttpStatus.CREATED,
            "SUCCESS_201",
            "리소스가 생성되었습니다."),

    ACCEPTED(HttpStatus.ACCEPTED,
            "SUCCESS_202",
            "요청을 접수했습니다."),

    NO_CONTENT(HttpStatus.NO_CONTENT,
            "SUCCESS_204",
            "콘텐츠가 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
