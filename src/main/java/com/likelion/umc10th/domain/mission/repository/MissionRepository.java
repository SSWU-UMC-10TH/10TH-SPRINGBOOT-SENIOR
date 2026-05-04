package com.likelion.umc10th.domain.mission.repository;

import com.likelion.umc10th.domain.mission.entity.Mission;
import com.likelion.umc10th.domain.mission.entity.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    // 내가 진행중/완료한 미션 목록 조회
    @Query("SELECT mm FROM MemberMission mm " +
            "JOIN FETCH mm.mission m " +
            "JOIN FETCH m.store s " +
            "WHERE mm.member.id = :memberId AND mm.isCompleted = :isCompleted")
    Page<MemberMission> findAllByMemberIdAndIsCompleted(
            @Param("memberId") Long memberId,
            @Param("isCompleted") Boolean isCompleted,
            Pageable pageable);

    // 현재 지역에서 도전 가능한 미션 목록 조회
    @Query("SELECT m FROM Mission m " +
            "JOIN FETCH m.store s " +
            "JOIN FETCH s.region r " +
            "WHERE r.id = :regionId AND m.deadline > CURRENT_TIMESTAMP")
    Page<Mission> findAllByRegionId(@Param("regionId") Integer regionId, Pageable pageable);

    // 완료한 미션 갯수 카운트
    @Query("SELECT COUNT(mm) FROM MemberMission mm WHERE mm.member.id = :memberId AND mm.isCompleted = true")
    Long countCompletedMissions(@Param("memberId") Long memberId);
}
