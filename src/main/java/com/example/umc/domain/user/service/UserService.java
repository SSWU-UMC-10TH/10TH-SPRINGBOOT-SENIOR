package com.example.umc.domain.user.service;

import com.example.umc.domain.user.dto.MyPageResponse;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Long userId) {
        User user = userRepository.findMyPageByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        return new MyPageResponse(
                user.getId(),
                user.getNickname(),
                user.getAuth().getEmail(),
                user.getPhoneNumber(),
                user.getPhoneVerified(),
                user.getPoint()
        );
    }
}