package com.example.usermanagementms.repository;

import com.example.usermanagementms.domain.VerificationOtp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationOTPRepository extends JpaRepository<VerificationOtp, Long> {
    Optional<VerificationOtp> findByUserId(Long userId);
}
