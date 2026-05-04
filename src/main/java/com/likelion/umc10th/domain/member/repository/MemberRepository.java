package com.likelion.umc10th.domain.member.repository;

import com.likelion.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
