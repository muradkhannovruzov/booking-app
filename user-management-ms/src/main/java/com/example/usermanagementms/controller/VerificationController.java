package com.example.usermanagementms.controller;

import com.example.usermanagementms.response.BaseResponse;
import com.example.usermanagementms.service.OtpPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final OtpPublisherService otpPublisherService;


    @PostMapping("/send-sms-otp")
    public BaseResponse<Void> sendSmsOtp
            (@RequestHeader("Authorization") String token) {

        return BaseResponse.success();
    }

    @PostMapping("/send-email-otp")
    public BaseResponse<Void> sendEmailOtp
            (@RequestHeader("Authorization") String token) {
        return null;
    }

    @PostMapping("/verify-phone")
    public BaseResponse<Boolean> sendSmsOtp() {
        return null;
    }

    @PostMapping("/verify-email")
    public BaseResponse<Boolean> sendEmailOtp() {
        return null;
    }
}