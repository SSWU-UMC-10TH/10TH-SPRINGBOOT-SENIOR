package com.likelion.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class MissionReqDTO {

    @Builder
    public record MissionStatusDTO(
            @NotBlank(message = "변경할 상태값은 필수입니다.")
            String status
    ) {}
}
