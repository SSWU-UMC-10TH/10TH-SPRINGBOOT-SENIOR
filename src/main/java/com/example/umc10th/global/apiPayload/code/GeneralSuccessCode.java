package com.example.umc10th.global.apiPayload.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {


    OK(HttpStatus.OK,
            "COMMON200_1",
            "요청에 성공했습니다."),


    CREATED(HttpStatus.CREATED,
            "COMMON201_1",
            "요청한 리소스가 생성되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}