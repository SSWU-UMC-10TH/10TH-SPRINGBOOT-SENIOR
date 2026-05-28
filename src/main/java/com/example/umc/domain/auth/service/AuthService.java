package com.example.umc.domain.auth.service;

import com.example.umc.domain.auth.dto.LoginRequest;
import com.example.umc.domain.auth.dto.LoginResponse;
import com.example.umc.domain.auth.dto.SignupRequest;
import com.example.umc.domain.auth.dto.SignupResponse;
import com.example.umc.domain.auth.entity.Auth;
import com.example.umc.domain.auth.repository.AuthRepository;
import com.example.umc.domain.terms.entity.Terms;
import com.example.umc.domain.terms.repository.TermsRepository;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.entity.UserAddress;
import com.example.umc.domain.user.entity.UserTerms;
import com.example.umc.domain.user.enums.Gender;
import com.example.umc.domain.user.repository.UserAddressRepository;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.domain.user.repository.UserTermsRepository;
import com.example.umc.global.apiPayload.code.GeneralErrorCode;
import com.example.umc.global.apiPayload.exception.ProjectException;
import com.example.umc.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final Long DEFAULT_TERMS_ID = 1L;

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserTermsRepository userTermsRepository;
    private final TermsRepository termsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public SignupResponse signup(SignupRequest req) {
        if (authRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        LocalDate birth = LocalDate.parse(
                req.getBirth(),
                DateTimeFormatter.ofPattern("yyyyMMdd")
        );

        User user = User.create(
                req.getName(),
                Gender.valueOf(req.getGender()),
                birth
        );

        String encodedPassword = passwordEncoder.encode(req.getPassword());

        Auth auth = Auth.createLocalAuth(
                req.getEmail(),
                encodedPassword
        );

        user.assignAuth(auth);

        User savedUser = userRepository.save(user);

        UserAddress address = UserAddress.create(
                savedUser,
                req.getPostalCode(),
                req.getBaseAddress(),
                req.getDetailAddress()
        );

        userAddressRepository.save(address);

        Terms terms = termsRepository.findById(DEFAULT_TERMS_ID)
                .orElseThrow(() -> new IllegalArgumentException("약관 정보를 찾을 수 없습니다."));

        UserTerms userTerms = UserTerms.agree(savedUser, terms);

        userTermsRepository.save(userTerms);

        return new SignupResponse(
                savedUser.getId(),
                savedUser.getAuth().getId(),
                savedUser.getAuth().getEmail(),
                savedUser.getNickname()
        );
    }

    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest req) {
        Auth auth = authRepository.findByEmail(req.email())
                .orElseThrow(() -> new ProjectException(GeneralErrorCode.UNAUTHORIZED));

        if (!passwordEncoder.matches(req.password(), auth.getPassword())) {
            throw new ProjectException(GeneralErrorCode.UNAUTHORIZED);
        }

        String accessToken = jwtUtil.createAccessToken(auth);

        return new LoginResponse(accessToken);
    }
}
