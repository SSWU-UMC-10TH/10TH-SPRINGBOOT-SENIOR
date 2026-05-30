package com.likelion.umc10th.domain.member.dto;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.enums.Address;
import com.likelion.umc10th.domain.member.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    @Builder
    public record SignUpDTO(
            @NotBlank(message = "이름은 필수 입력 항목입니다.")
            @Size(max = 50)
            String name,

            @NotBlank(message = "닉네임은 필수 입력 항목입니다.")
            @Size(max = 50)
            String nickname,

            @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
            String password,

            @NotNull(message = "생년월일은 필수 입력 항목입니다.")
            LocalDate birth,

            @NotBlank(message = "성별을 선택해주세요. (MALE 또는 FEMALE)")
            String gender,

            @NotBlank(message = "주소를 입력해주세요.")
            String address,

            @Size(max = 255)
            String addressDetail,

            @NotNull(message = "선호 음식을 하나 이상 선택해주세요.")
            List<Long> foodIds
    ) {
        public Member toEntity(String encodedPassword) {
            return Member.builder()
                    .name(name)
                    .nickname(nickname)
                    .password(encodedPassword)
                    .gender(Gender.valueOf(gender.toUpperCase()))
                    .birth(birth)
                    .address(Address.valueOf(address.toUpperCase()))
                    .addressDetail(addressDetail)
                    .point(0L)
                    .build();
    }
    }
    @Builder
    public record LoginDTO(
            @NotBlank(message = "이름(ID)은 필수 입력 항목입니다.")
            String name,

            @NotBlank(message = "비밀번호는 필수 입력 항목입니다.")
            String password
    ) {}
}
