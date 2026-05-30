package com.likelion.umc10th.domain.mission.exception;

import com.likelion.umc10th.global.apiPayload.code.BaseErrorCode;
import com.likelion.umc10th.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
