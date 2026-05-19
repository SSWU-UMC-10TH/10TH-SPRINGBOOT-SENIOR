package com.example.umc.domain.user.entity;

import com.example.umc.domain.terms.entity.Terms;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user_terms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserTerms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_terms_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "terms_id", nullable = false)
    private Terms terms;

    @Column(name = "agreed", nullable = false)
    private Boolean agreed = true;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    public static UserTerms agree(User user, Terms terms) {
        UserTerms userTerms = new UserTerms();
        userTerms.user = user;
        userTerms.terms = terms;
        userTerms.agreed = true;
        userTerms.agreedAt = LocalDateTime.now();
        return userTerms;
    }
}
