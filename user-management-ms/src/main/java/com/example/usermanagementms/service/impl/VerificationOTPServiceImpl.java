package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.VerificationOTP;
import com.example.usermanagementms.domain.enums.VerificationType;
import com.example.usermanagementms.repository.VerificationOTPRepository;
import com.example.usermanagementms.service.VerificationOTPService;
import com.example.usermanagementms.utils.OTPUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class VerificationOTPServiceImpl implements VerificationOTPService {

    private final VerificationOTPRepository otpRepository;

    private final long otpExpiration = 1000 * 3; // 3 minutes

    @Override
    public VerificationOTP createForUser(User user, VerificationType verificationType) {
        String otpCode = OTPUtils.generate();
        LocalDateTime expiryDate = LocalDateTime.now().plusNanos(otpExpiration);
        VerificationOTP otp = new VerificationOTP();
        otp.setOtp(otpCode);
        otp.setUser(user);
        otp.setExpiryDate(expiryDate);
        otp.setVerficationType(verificationType);

        return otpRepository.save(otp);
    }
}
