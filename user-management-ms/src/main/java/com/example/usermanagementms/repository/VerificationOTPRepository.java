package com.example.usermanagementms.repository;

import com.example.usermanagementms.domain.VerificationOtp;
import com.example.usermanagementms.domain.enums.VerificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VerificationOTPRepository extends JpaRepository<VerificationOtp, Long> {
    Optional<VerificationOtp> findByUserId(Long userId);
    Optional<VerificationOtp> findFirstByUserIdAndVerficationTypeOrderByCreatedDateDesc
            (Long userId, VerificationType verficationType);

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
