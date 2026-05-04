package com.likelion.umc10th.domain.mission.service;

import com.likelion.umc10th.domain.mission.dto.MissionResDTO;
import com.likelion.umc10th.domain.mission.entity.mapping.MemberMission;
import com.likelion.umc10th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResDTO.MyMissionListDTO getMyMissionList(Long memberId, Boolean isCompleted, Integer page) {

        Pageable pageable = PageRequest.of(page > 0 ? page - 1 : 0, 10, Sort.by("createdAt").descending());
        Page<MemberMission> mmPage = missionRepository.findAllByMemberIdAndIsCompleted(memberId, isCompleted, pageable);

        List<MissionResDTO.MyMissionDetailDTO> missionDetailDTOList = mmPage.getContent().stream()
                .map(mm -> MissionResDTO.MyMissionDetailDTO.builder()
                        .userMissionId(mm.getId())
                        .point(mm.getMission().getPoint().intValue())
                        .storeName(mm.getMission().getStore().getName())
                        .status(mm.getIsCompleted() ? "COMPLETED" : "CHALLENGING")
                        .condition(mm.getMission().getCondition())
                        .createdAt(mm.getCreatedAt().toLocalDate())
                        .build()
                ).toList();

        return MissionResDTO.MyMissionListDTO.builder()
                .missionList(missionDetailDTOList)
                .totalPages(mmPage.getTotalPages())
                .totalElements(mmPage.getTotalElements())
                .isLast(mmPage.isLast())
                .build();
    }


}
