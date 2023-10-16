package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.VerificationOtp;
import com.example.usermanagementms.domain.enums.VerificationType;
import com.example.usermanagementms.repository.VerificationOTPRepository;
import com.example.usermanagementms.service.VerificationOtpService;
import com.example.usermanagementms.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class VerificationOtpServiceImpl implements VerificationOtpService {

    private final VerificationOTPRepository otpRepository;

    private final long otpExpiration = 1000 * 3; // 3 minutes

    @Override
    public VerificationOtp createForUser(User user, VerificationType verificationType) {
        String otpCode = OtpUtil.generate();
        LocalDateTime expiryDate = LocalDateTime.now().plusNanos(otpExpiration);
        VerificationOtp otp = new VerificationOtp();
        otp.setOtp(otpCode);
        otp.setUser(user);
        otp.setExpiryDate(expiryDate);
        otp.setVerficationType(verificationType);

        return otpRepository.save(otp);
    }

    @Override
    public VerificationOtp getLatestOtpByUserIdAndType(Long userId, VerificationType verificationType) {
        return otpRepository.findFirstByUserIdAndVerficationTypeOrderByCreatedDateDesc(userId, verificationType)
                .orElseThrow(() -> new RuntimeException("No OTP found for user"));

    }

    @Override
    public boolean isOtpExpired(VerificationOtp otp) {
        return otp.getExpiryDate().isBefore(LocalDateTime.now());
    }


}
