package com.likelion.umc10th.domain.member.service;

import com.likelion.umc10th.domain.member.dto.MemberResDTO;
import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.repository.MemberRepository;
import com.likelion.umc10th.domain.mission.entity.Mission;
import com.likelion.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    public MemberResDTO.HomeViewDTO getHomeView(Long memberId, Integer regionId, Integer page) {
        // 회원 정보 및 포인트
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));

        // 완료 미션 카운트
        Integer completedCount = missionRepository.countCompletedMissions(memberId).intValue();

        // 하단 지역별 미션 리스트 (페이징)
        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, 10, Sort.by("createdAt").descending());
        Page<Mission> missionPage = missionRepository.findAllByRegionId(regionId, pageable);

        // 지역명 가져오기
        String regionName = missionPage.isEmpty() ? "알 수 없는 지역" :
                missionPage.getContent().get(0).getStore().getRegion().getName().name();

        List<MemberResDTO.HomeMissionSummaryDTO> missionSummaryList = missionPage.getContent().stream()
                .map(m -> MemberResDTO.HomeMissionSummaryDTO.builder()
                        .userMissionId(m.getId())
                        .storeName(m.getStore().getName())
                        .condition(m.getCondition())
                        .point(m.getPoint().intValue())
                        .deadlineDDay(calculateDDay(m.getDeadline()))
                        .status("READY")
                        .build()
                ).toList();

        return MemberResDTO.HomeViewDTO.builder()
                .currentRegion(regionName)
                .totalPoints(member.getPoint().intValue()) //
                .completedMissions(completedCount)
                .targetMissions(10)
                .missionData(MemberResDTO.HomeMissionListDTO.builder()
                        .missionList(missionSummaryList)
                        .totalPages(missionPage.getTotalPages())
                        .totalElements(missionPage.getTotalElements())
                        .isLast(missionPage.isLast())
                        .build())
                .build();
    }

    private String calculateDDay(LocalDateTime deadline) {
        long dDay = ChronoUnit.DAYS.between(LocalDateTime.now(), deadline);
        return dDay >= 0 ? "D-" + dDay : "기간 만료";
    }

    public MemberResDTO.MyPageDTO getMyPageView(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("해당 회원이 없습니다."));

        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname()) // name 대신 nickname 사용!
                .socialEmail(member.getSocialEmail() != null ? member.getSocialEmail() : "이메일 정보 없음")
                .totalPoints(member.getPoint().intValue())
                .build();
    }
}
