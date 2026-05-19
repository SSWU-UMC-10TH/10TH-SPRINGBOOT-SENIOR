package com.example.umc.domain.auth.repository;

import com.example.umc.domain.auth.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth, Long> {

    boolean existsByEmail(String email);

    Optional<Auth> findByEmail(String email);
}