package com.example.providerms.service.impl;

import com.example.providerms.domain.Provider;
import com.example.providerms.domain.VerificationOtp;
import com.example.providerms.domain.enums.VerificationType;
import com.example.providerms.repository.VerificationOTPRepository;
import com.example.providerms.service.VerificationOtpService;
import com.example.providerms.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class VerificationOtpServiceImpl implements VerificationOtpService {

    private final VerificationOTPRepository otpRepository;

    private final long otpExpiration = 1000 * 3; // 3 minutes

    @Override
    public VerificationOtp createForUser(Provider provider, VerificationType verificationType) {
        String otpCode = OtpUtil.generate();
        LocalDateTime expiryDate = LocalDateTime.now().plusNanos(otpExpiration);
        VerificationOtp otp = new VerificationOtp();
        otp.setOtp(otpCode);
        otp.setUser(provider);
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
        return otp.getExpiryDate().isBefore(LocalDateTime.now());
    }


}
