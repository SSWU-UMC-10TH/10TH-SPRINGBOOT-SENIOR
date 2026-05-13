package com.example.umc.domain.user.repository;

import com.example.umc.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("""
        SELECT u
        FROM User u
        LEFT JOIN FETCH u.auth a
        WHERE u.id = :userId
    """)
    Optional<User> findMyPageByUserId(@Param("userId") Long userId);
}
