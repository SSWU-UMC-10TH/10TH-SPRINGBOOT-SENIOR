package com.example.umc.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CompleteMissionRequest {

    @NotBlank(message = "상태값은 필수입니다.")
    @Pattern(regexp = "COMPLETED", message = "상태값은 COMPLETED만 가능합니다.")
    private String status;
}