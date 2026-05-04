package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_OK(HttpStatus.OK, "MISSION200", "미션 목록 조회에 성공했습니다."),
    MISSION_COMPLETE_OK(HttpStatus.OK, "MISSION201", "미션 완료 처리에 성공했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}