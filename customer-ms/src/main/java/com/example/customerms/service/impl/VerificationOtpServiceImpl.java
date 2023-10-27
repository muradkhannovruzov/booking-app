package com.example.customerms.service.impl;

import com.example.customerms.domain.Customer;
import com.example.customerms.domain.VerificationOtp;
import com.example.customerms.domain.enums.VerificationType;
import com.example.customerms.repository.VerificationOTPRepository;
import com.example.customerms.service.VerificationOtpService;
import com.example.customerms.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class VerificationOtpServiceImpl implements VerificationOtpService {

    private final VerificationOTPRepository otpRepository;

    private final long otpExpiration = 1000 * 3; // 3 minutes

    @Override
    public VerificationOtp createForUser(Customer customer, VerificationType verificationType) {
        String otpCode = OtpUtil.generate();
        LocalDateTime expiryDate = LocalDateTime.now().plusNanos(otpExpiration);
        VerificationOtp otp = new VerificationOtp();
        otp.setOtp(otpCode);
        otp.setUser(customer);
        otp.setExpiryDate(expiryDate);
        otp.setVerificationType(verificationType);

        return otpRepository.save(otp);
    }

    @Override
    public VerificationOtp getLatestOtpByUserIdAndType(Long userId, VerificationType verificationType) {
        return otpRepository.findFirstByUserIdAndVerificationTypeOrderByCreatedDateDesc(userId, verificationType)
                .orElseThrow(() -> new RuntimeException("No OTP found for user"));

    }

    @Override
    public boolean isOtpExpired(VerificationOtp otp) {
        return otp.getExpiryDate().isAfter(LocalDateTime.now());
    }


}
