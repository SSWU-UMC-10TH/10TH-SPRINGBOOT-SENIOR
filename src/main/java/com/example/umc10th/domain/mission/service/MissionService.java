package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionReqDto;
import com.example.umc10th.domain.mission.dto.MissionResDto;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.Store;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;

    public Page<MissionResDto.MyMissionDto> getMyMissions(
            Long userId,
            Boolean isSuccess,
            Pageable pageable
    ) {
        Long memberId = userId;

        Page<MemberMission> memberMissions =
                memberMissionRepository.findByMemberIdAndIsSuccess(
                        memberId,
                        isSuccess,
                        pageable
                );

        return memberMissions.map(memberMission -> {
            Mission mission = memberMission.getMission();
            Store store = mission.getStore();

            Long reviewId = reviewRepository
                    .findByMemberIdAndStoreId(memberId, store.getId())
                    .map(Review::getId)
                    .orElse(null);

            return new MissionResDto.MyMissionDto(
                    mission.getId(),
                    mission.getRewardPoint(),
                    store.getName(),
                    mission.getMissionCondition(),
                    memberMission.getIsSuccess(),
                    reviewId
            );
        });
    }
    @Transactional
    public void completeMission(Long missionId) {

        Long memberId = 1L; // TODO: JWT

        Mission mission = memberMissionRepository
                .findByMemberIdAndMissionId(memberId, missionId)
                .orElseThrow(() -> new RuntimeException("참여중인 미션 없음")).getMission();

        mission.setIsSuccess(true);
    }
}