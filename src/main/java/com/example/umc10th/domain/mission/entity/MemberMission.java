package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Entity
    public class MemberMission {

        @Id
        @GeneratedValue
        private Long id;

        @ManyToOne
        private Member member;

        @ManyToOne
        private Mission mission;

        private Boolean isSuccess;

        public void complete() {
            this.isSuccess = true;
        }
    }

