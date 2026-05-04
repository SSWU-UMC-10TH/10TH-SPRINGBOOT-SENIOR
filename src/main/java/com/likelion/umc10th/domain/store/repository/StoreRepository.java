package com.likelion.umc10th.domain.store.repository;

import com.likelion.umc10th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
