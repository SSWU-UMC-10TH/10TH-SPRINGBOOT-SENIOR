package com.likelion.umc10th.domain.member.entity;

import com.likelion.umc10th.domain.member.entity.mapping.MemberFood;
import com.likelion.umc10th.domain.member.entity.mapping.MemberTerm;
import com.likelion.umc10th.domain.member.enums.Address;
import com.likelion.umc10th.domain.member.enums.Gender;
import com.likelion.umc10th.domain.member.enums.SocialType;
import com.likelion.umc10th.global.config.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private Gender gender;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "social_email", unique = true, length = 100, nullable = true)
    private String socialEmail;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    @Column(name = "address", nullable = false, length = 20)
    private Address address;

    @Column(name = "address_detail", length = 255)
    private String addressDetail;

    @Builder.Default
    @Column(name = "point", nullable = false)
    private Long point = 0L;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type", length = 20, nullable = true)
    private SocialType socialType;


}
