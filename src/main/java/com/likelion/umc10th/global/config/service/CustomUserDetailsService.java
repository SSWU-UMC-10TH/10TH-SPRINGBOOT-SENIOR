package com.likelion.umc10th.global.config.service;

import com.likelion.umc10th.domain.member.entity.Member;
import com.likelion.umc10th.domain.member.exception.MemberException;
import com.likelion.umc10th.domain.member.exception.code.MemberErrorCode;
import com.likelion.umc10th.domain.member.repository.MemberRepository;
import com.likelion.umc10th.global.config.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername (
            String username
    ) throws UsernameNotFoundException{
        Member member = memberRepository.findByName(username)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
            return new AuthMember(member);
    }
}
