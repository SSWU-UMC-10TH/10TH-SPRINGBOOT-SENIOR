package com.example.umc.global.apiPayload.handler;

import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.ProjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionAdvice {

    @ExceptionHandler(ProjectException.class)
    public ResponseEntity<ApiResponse<Void>> handleProjectException(
            ProjectException e
    ) {
        BaseErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, errors));
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ApiResponse<String>> handleHandlerMethodValidationException(
            HandlerMethodValidationException e
    ) {
        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, "요청 파라미터 검증에 실패했습니다."));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(
            IllegalArgumentException e
    ) {
        BaseErrorCode errorCode = GeneralErrorCode.BAD_REQUEST;

        return ResponseEntity.status(errorCode.getStatus())
                .body(ApiResponse.onFailure(errorCode, e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleException(
            Exception ex
    ) {
        BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;

        return ResponseEntity.status(code.getStatus())
                .body(ApiResponse.onFailure(code, ex.getMessage()));
    }
}