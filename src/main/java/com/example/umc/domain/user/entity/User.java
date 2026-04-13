package com.example.umc.domain.user.entity;

import com.example.umc.domain.auth.entity.Auth;
import com.example.umc.domain.user.enums.Gender;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    @Column(name = "phone_verified", nullable = false)
    private Boolean phoneVerified = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.PRIVATE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "point", nullable = false)
    private Integer point = 0;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Auth auth;

    public void assignAuth(Auth auth) {
        this.auth = auth;
        auth.attachUser(this);
    }
}