package com.loyalty_jj.repository;

import com.loyalty_jj.model.Loyalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoyaltyRepository extends JpaRepository<Loyalty, Long> {

    Optional<Loyalty> findByUserId(Long userId);
}