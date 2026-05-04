package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    Page<Mission> findByStoreRegionAndDeadlineAfter(
            String region,
            LocalDate deadline,
            Pageable pageable
    );



}
