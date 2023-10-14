package com.example.usermanagementms.service;

public interface VerificationOTPService {
    void sendOTP(String key, String value, String otp);
    boolean verifyOTP(String key, String otp);
}
