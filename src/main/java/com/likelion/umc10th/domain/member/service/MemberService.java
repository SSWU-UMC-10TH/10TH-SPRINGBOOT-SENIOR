package com.likelion.umc10th.domain.member.service;

import com.likelion.umc10th.domain.member.dto.MemberReqDTO;
import com.likelion.umc10th.domain.member.dto.MemberResDTO;
import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.exception.MemberException;
import com.likelion.umc10th.domain.member.exception.code.MemberErrorCode;
import com.likelion.umc10th.domain.member.repository.MemberRepository;
import com.likelion.umc10th.domain.mission.entity.Mission;
import com.likelion.umc10th.domain.mission.repository.MissionRepository;
import com.likelion.umc10th.global.config.entity.AuthMember;
import com.likelion.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public MemberResDTO.HomeViewDTO getHomeView(Long memberId, Integer regionId, Integer page) {
        // 회원 정보 및 포인트
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

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
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberResDTO.MyPageDTO.builder()
                .nickname(member.getNickname()) // name 대신 nickname 사용!
                .socialEmail(member.getSocialEmail() != null ? member.getSocialEmail() : "이메일 정보 없음")
                .totalPoints(member.getPoint().intValue())
                .build();
    }

    @Transactional
    public MemberResDTO.SignUpResultDTO joinMember(MemberReqDTO.SignUpDTO request) {
        // 1. name(ID 역할을 하는 이름) 중복 체크
        memberRepository.findByName(request.name())
                .ifPresent(member -> {
                    throw new MemberException(MemberErrorCode.DUPLICATE_USERNAME);
                });

        // 2. 비밀번호 BCrypt 암호화 (Salt 처리 자동 포함)
        String encodedPassword = passwordEncoder.encode(request.password());

        // 3. 엔티티 생성 및 저장
        Member newMember = request.toEntity(encodedPassword);
        Member savedMember = memberRepository.save(newMember);

        return MemberResDTO.SignUpResultDTO.builder()
                .memberId(savedMember.getId())
                .createdAt(savedMember.getCreatedAt())
                .build();
    }

    @Transactional(readOnly = true) // 로그인 등 단순 조회 요청은 최적화를 위해 readOnly 권장
    public MemberResDTO.LoginResultDTO loginMember(MemberReqDTO.LoginDTO request) {

        // 1. name(사용자 ID)으로 DB에서 회원 조회
        Member member = memberRepository.findByName(request.name())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        // 💡 MemberErrorCode에 정의된 알맞은 에러코드를 사용해 주세요!

        // 2. 비번 일치 여부
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID_PASSWORD);
        }

        AuthMember authMember = new AuthMember(member);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return MemberResDTO.LoginResultDTO.builder()
                .memberId(member.getId())
                .accessToken(accessToken)
                .tokenType("Bearer")
                .build();
    }

}
