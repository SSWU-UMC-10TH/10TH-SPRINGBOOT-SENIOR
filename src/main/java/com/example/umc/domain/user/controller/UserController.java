package com.example.umc.domain.user.controller;

import com.example.umc.domain.user.dto.MyPageResponse;
import com.example.umc.domain.user.service.UserService;
import com.example.umc.global.apiPayload.ApiResponse;
import com.example.umc.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/my-page")
    public ApiResponse<MyPageResponse> getMyPage(
            @RequestParam Long userId
    ) {
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, userService.getMyPage(userId));
    }
}