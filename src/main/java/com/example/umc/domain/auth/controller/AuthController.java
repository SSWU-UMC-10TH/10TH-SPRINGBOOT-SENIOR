package com.example.umc.domain.auth.controller;

import com.example.umc.domain.auth.dto.LoginRequest;
import com.example.umc.domain.auth.dto.LoginResponse;
import com.example.umc.domain.auth.dto.SignupRequest;
import com.example.umc.domain.auth.dto.SignupResponse;
import com.example.umc.domain.auth.service.AuthService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<SignupResponse> signup(
            @RequestBody @Valid SignupRequest req
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, authService.signup(req));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @RequestBody LoginRequest req
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, authService.login(req));
    }
}
