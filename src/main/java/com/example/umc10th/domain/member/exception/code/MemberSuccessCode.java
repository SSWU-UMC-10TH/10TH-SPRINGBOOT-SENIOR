package com.example.umc10th.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode {
    OK(HttpStatus.OK, "MEMBER200", "요청에 성공했습니다.");
    private final HttpStatus status;
    private final String code;
    private final String message;
}
