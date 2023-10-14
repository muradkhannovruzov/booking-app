package com.example.usermanagementms.repository;

import com.example.usermanagementms.domain.VerificationOTP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationOTPRepository extends JpaRepository<VerificationOTP, Long> {
    Optional<VerificationOTP> findByUserId(Long userId);
}
