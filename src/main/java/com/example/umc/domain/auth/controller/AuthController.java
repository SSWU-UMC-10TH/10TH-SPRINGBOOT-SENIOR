package com.example.umc.domain.auth.controller;

import com.example.umc.domain.auth.dto.SignUpRequest;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ApiResponse<Void> signUp(@RequestBody @Valid SignUpRequest req) {
        return null;
    }
}
