package com.likelion.umc10th.domain.member.entity.mapping;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.entity.Term;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "members_terms")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTerm {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "term_id")
    private Term term;
}
