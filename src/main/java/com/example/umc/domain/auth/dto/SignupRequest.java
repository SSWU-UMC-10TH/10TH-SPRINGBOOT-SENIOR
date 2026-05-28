package com.example.umc.domain.auth.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @NotBlank(message = "성별은 필수입니다.")
    @Pattern(regexp = "M|F|PRIVATE", message = "성별은 M, F, PRIVATE만 가능합니다.")
    private String gender;

    @NotBlank(message = "이메일은 필수입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank(message = "생년월일은 필수입니다.")
    @Pattern(regexp = "^\\d{8}$", message = "생년월일은 yyyyMMdd 형식이어야 합니다.")
    private String birth;

    @NotBlank(message = "우편번호는 필수입니다.")
    private String postalCode;

    @NotBlank(message = "기본 주소는 필수입니다.")
    private String baseAddress;

    private String detailAddress;

    @AssertTrue(message = "약관 동의는 필수입니다.")
    private Boolean agreeTerms;
}