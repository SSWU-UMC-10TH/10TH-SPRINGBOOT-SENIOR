package com.likelion.umc10th.domain.member.exception;

import com.likelion.umc10th.global.apiPayload.code.BaseErrorCode;
import com.likelion.umc10th.global.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
