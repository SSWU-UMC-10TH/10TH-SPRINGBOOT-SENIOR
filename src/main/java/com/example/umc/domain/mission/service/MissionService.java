package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.dto.HomeMissionListResponse;
import com.example.umc.domain.mission.dto.HomeMissionResponse;
import com.example.umc.domain.mission.dto.MyMissionListResponse;
import com.example.umc.domain.mission.dto.MyMissionResponse;
import com.example.umc.domain.mission.entity.UserMission;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionService {

    private static final int DEFAULT_REWARD_POINT = 500;

    private final UserMissionRepository userMissionRepository;

    @Transactional(readOnly = true)
    public MyMissionListResponse getMyMissions(Long userId, Long cursor, Integer size) {
        List<UserMission> result = userMissionRepository.findMyMissions(
                userId,
                List.of(MissionStatus.IN_PROGRESS, MissionStatus.COMPLETED),
                cursor == null ? 0L : cursor,
                PageRequest.of(0, size + 1)
        );

        boolean hasNext = result.size() > size;

        if (hasNext) {
            result = result.subList(0, size);
        }

        List<MyMissionResponse> missions = result.stream()
                .map(um -> new MyMissionResponse(
                        um.getId(),
                        um.getMission().getId(),
                        um.getMission().getStore().getName(),
                        um.getMission().getStore().getFoodCategory().getName(),
                        um.getMission().getName(),
                        um.getMission().getType(),
                        um.getMission().getDeadline(),
                        um.getState()
                ))
                .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).userMissionId();

        return new MyMissionListResponse(
                missions,
                hasNext ? nextCursor : null,
                hasNext
        );
    }

    @Transactional(readOnly = true)
    public HomeMissionListResponse getHomeMissions(Long regionId, Long cursor, Integer size) {
        List<UserMission> result = userMissionRepository.findHomeMissions(
                regionId,
                cursor == null ? 0L : cursor,
                PageRequest.of(0, size + 1)
        );

        boolean hasNext = result.size() > size;

        if (hasNext) {
            result = result.subList(0, size);
        }

        List<HomeMissionResponse> missions = result.stream()
                .map(um -> new HomeMissionResponse(
                        um.getId(),
                        um.getMission().getId(),
                        um.getMission().getStore().getName(),
                        um.getMission().getStore().getFoodCategory().getName(),
                        um.getMission().getName(),
                        um.getMission().getType(),
                        um.getMission().getDeadline(),
                        DEFAULT_REWARD_POINT
                ))
                .toList();

        Long nextCursor = missions.isEmpty()
                ? null
                : missions.get(missions.size() - 1).userMissionId();

        return new HomeMissionListResponse(
                missions,
                hasNext ? nextCursor : null,
                hasNext
        );
    }

    @Transactional
    public void completeMission(Long userId, Long missionId) {
        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자의 미션을 찾을 수 없습니다."));

        userMission.complete();
    }
}
