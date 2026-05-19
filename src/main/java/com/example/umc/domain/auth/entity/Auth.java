package com.example.umc.domain.auth.entity;

import com.example.umc.domain.auth.enums.SocialProvider;
import com.example.umc.domain.user.entity.User;
import com.example.umc.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "auth")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auth_id")
    private Long id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private SocialProvider provider;

    @Column(name = "provider_id", length = 50)
    private String providerId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    public Auth(String email, String password, SocialProvider provider, String providerId) {
        this.email = email;
        this.password = password;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void attachUser(User user) {
        this.user = user;
    }
}