package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.service.VerificationOTPService;
import org.springframework.stereotype.Service;


@Service
public class VerificationOTPServiceImpl implements VerificationOTPService {
    @Override
    public void sendOTP(String key, String value, String otp) {

    }

    @Override
    public boolean verifyOTP(String key, String otp) {
        return false;
    }
}
