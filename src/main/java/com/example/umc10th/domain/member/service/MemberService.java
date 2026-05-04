package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDto;
import com.example.umc10th.domain.member.dto.MemberResDto;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MemberResDto.GetInfo getInfo(MemberReqDto.GetInfo dto) {

        Long memberId = dto.id();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDto.HomeResDto getHome(String regionName, Pageable pageable) {

        Long memberId = 1L;

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        int completedCount =
                memberMissionRepository.countByMemberIdAndIsSuccessTrue(memberId);

        int totalGoalCount = 10;
        int nextRewardPoint = 1000;

        Page<Mission> missions =
                missionRepository.findByStoreRegionAndDeadlineAfter(
                        regionName,
                        LocalDate.now(),
                        pageable
                );

        Page<MemberResDto.HomeMissionDto> missionDtos = missions.map(mission -> {
            Store store = mission.getStore();

            long remainingDays = ChronoUnit.DAYS.between(
                    LocalDate.now(),
                    mission.getDeadline()
            );

            boolean isParticipating =
                    memberMissionRepository.existsByMemberIdAndMissionId(
                            memberId,
                            mission.getId()
                    );

            return new MemberResDto.HomeMissionDto(
                    mission.getId(),
                    store.getName(),
                    store.getCategory().toString(),
                    remainingDays,
                    mission.getMissionCondition(),
                    mission.getRewardPoint(),
                    isParticipating
            );
        });

        return new MemberResDto.HomeResDto(
                regionName,
                completedCount,
                totalGoalCount,
                nextRewardPoint,
                member.getPoint(),
                missionDtos
        );
    }
}