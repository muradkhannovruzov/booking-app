package com.example.providerms.service;

import com.example.providerms.domain.Provider;
import com.example.providerms.domain.VerificationOtp;
import com.example.providerms.domain.enums.VerificationType;

public interface VerificationOtpService {
    VerificationOtp createForUser(Provider provider, VerificationType verificationType);

    VerificationOtp getLatestOtpByUserIdAndType(Long userId, VerificationType verificationType);

    boolean isOtpExpired(VerificationOtp otp);
}
