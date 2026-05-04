package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMemberIdAndIsSuccess(
            Long memberId,
            Boolean isSuccess,
            Pageable pageable
    );

    int countByMemberIdAndIsSuccessTrue(Long memberId);

    boolean existsByMemberIdAndMissionId(Long memberId, Long missionId);

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);
}