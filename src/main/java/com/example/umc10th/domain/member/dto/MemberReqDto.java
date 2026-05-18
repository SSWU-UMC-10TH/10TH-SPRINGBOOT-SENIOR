package com.example.umc10th.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class MemberReqDto {

    public  record  GetInfo(
            Long id
    ){}
    public record SignUpDto(
            String email,
            String pw,
            String name,
            LocalDate birth,
            String gender,
            String address,

            @JsonProperty("detail_address")
            String detailAddress,
            @JsonProperty("phone_num")
            String phoneNum,




            // 약관 동의
            Boolean agreeAge,
            Boolean agreeTerms,
            Boolean agreePrivacy,
            Boolean agreeLocation,
            Boolean agreeMarketing,

            // 선호 음식
            Boolean korean,
            Boolean chinese,
            Boolean japanese,
            Boolean western,
            Boolean snack,
            Boolean grilledMeat,
            Boolean sushi,
            Boolean lateNight,
            Boolean fastFood,
            Boolean dessert,
            Boolean asianFood


    ) {}
    public record MyPageDto(
            String nickname,
            String email,
            String phoneNumber,
            Boolean phoneVerified,
            Integer point
    ) {}

}
