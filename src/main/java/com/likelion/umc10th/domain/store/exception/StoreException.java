package com.likelion.umc10th.domain.store.exception;

import com.likelion.umc10th.global.apiPayload.code.BaseErrorCode;
import com.likelion.umc10th.global.apiPayload.exception.ProjectException;


public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
