package com.example.customerms.repository;

import com.example.customerms.domain.VerificationOtp;
import com.example.customerms.domain.enums.VerificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationOTPRepository extends JpaRepository<VerificationOtp, Long> {
    Optional<VerificationOtp> findByUserId(Long userId);
    Optional<VerificationOtp> findFirstByUserIdAndVerificationTypeOrderByCreatedDateDesc
            (Long userId, VerificationType verificationType);

//    @Query(value = "SELECT * " +
//            "FROM verification_otp v " +
//            "WHERE v.user_id = :userId " +
//            "AND v.verfication_type = :type " +
//            "ORDER BY v.created_date DESC " +
//            "LIMIT 1",
//            nativeQuery = true)
//    Optional<VerificationOtp> findLatestOtpByUserIdAndType
//            (@Param("userId") Long userId, @Param("type") String type);

}
