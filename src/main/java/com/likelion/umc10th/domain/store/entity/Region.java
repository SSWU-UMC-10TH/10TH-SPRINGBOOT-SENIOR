package com.likelion.umc10th.domain.store.entity;

import com.likelion.umc10th.domain.member.enums.Address;
import com.likelion.umc10th.domain.store.enums.RegionName;
import com.likelion.umc10th.global.config.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "regions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private RegionName name;
}
