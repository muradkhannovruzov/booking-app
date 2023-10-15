package com.example.usermanagementms.controller;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.response.BaseResponse;
import com.example.usermanagementms.service.OtpPublisherService;
import com.example.usermanagementms.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final OtpPublisherService otpPublisherService;


    @PostMapping("/send-sms-otp")
    public BaseResponse<Void> sendSmsOtp
            (@RequestHeader("Authorization") String authHeader) {
        User user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishPhoneOtp(user);
        return BaseResponse.success();
    }

    @PostMapping("/send-email-otp")
    public BaseResponse<Void> sendEmailOtp
            (@RequestHeader("Authorization") String authHeader) {
        User user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishEmailOtp(user);
        return BaseResponse.success();
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