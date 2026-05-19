package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.dto.InProgressMissionResponse;
import com.example.umc.domain.mission.enums.MissionStatus;
import com.example.umc.domain.mission.entity.UserMission;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.foodCategory fc
        WHERE um.user.id = :userId
          AND um.state IN :statuses
          AND um.id > :cursor
        ORDER BY um.id ASC
    """)
    List<UserMission> findMyMissions(
            @Param("userId") Long userId,
            @Param("statuses") List<MissionStatus> statuses,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
        SELECT um
        FROM UserMission um
        JOIN FETCH um.mission m
        JOIN FETCH m.store s
        JOIN FETCH s.region r
        WHERE r.id = :regionId
          AND um.state = com.example.umc.domain.mission.enums.MissionStatus.NOT_STARTED
          AND um.id > :cursor
        ORDER BY um.id ASC
    """)
    List<UserMission> findHomeMissions(
            @Param("regionId") Long regionId,
            @Param("cursor") Long cursor,
            Pageable pageable
    );

    @Query("""
        SELECT um
        FROM UserMission um
        WHERE um.user.id = :userId
          AND um.mission.id = :missionId
    """)
    Optional<UserMission> findByUserIdAndMissionId(
            @Param("userId") Long userId,
            @Param("missionId") Long missionId
    );

    @Query("""
        SELECT new com.example.umc.domain.mission.dto.InProgressMissionResponse(
            um.id,
            m.id,
            m.name,
            m.type,
            s.name,
            m.deadline
        )
        FROM UserMission um
        JOIN um.mission m
        JOIN m.store s
        WHERE um.user.id = :userId
          AND um.state = :status
        ORDER BY um.id ASC
    """)
    Slice<InProgressMissionResponse> findInProgressMissions(
            @Param("userId") Long userId,
            @Param("status") MissionStatus status,
            Pageable pageable
    );
}