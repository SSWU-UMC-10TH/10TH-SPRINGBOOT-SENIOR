package com.example.umc10th.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MemberReqDto {

    public  record  GetInfo(
            Long id
    ){}
    public record SignUpDto(
            String email,
            String pw,
            String name,
            String birth,
            String gender,

            @JsonProperty("phone_num")
            String phoneNum,

            String address,

            @JsonProperty("detail_address")
            String detailAddress,

            @JsonProperty("alarm_event")
            Boolean alarmEvent,

            @JsonProperty("alarm_review_reply")
            Boolean alarmReviewReply,

            @JsonProperty("alarm_qna_reply")
            Boolean alarmQnaReply
    ) {}
    public record MyPageDto(
            String nickname,
            String email,
            String phoneNumber,
            Boolean phoneVerified,
            Integer point
    ) {}

}
