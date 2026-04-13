package com.example.umc.domain.mission.controller;

import com.example.umc.domain.mission.dto.CompleteMissionRequest;
import com.example.umc.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @GetMapping
    public ApiResponse<Void> getMissions(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        return null;
    }

    @PatchMapping("/{missionId}")
    public ApiResponse<Void> completeMission(
            @PathVariable Long missionId,
            @RequestBody @Valid CompleteMissionRequest req
    ) {
        return null;
    }
}