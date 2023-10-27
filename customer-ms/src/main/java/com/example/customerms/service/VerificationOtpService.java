package com.example.customerms.service;


import com.example.customerms.domain.Customer;
import com.example.customerms.domain.VerificationOtp;
import com.example.customerms.domain.enums.VerificationType;

public interface VerificationOtpService {
    VerificationOtp createForUser(Customer provider, VerificationType verificationType);

    VerificationOtp getLatestOtpByUserIdAndType(Long userId, VerificationType verificationType);

    boolean isOtpExpired(VerificationOtp otp);
}
