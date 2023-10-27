package com.example.customerms.controller;

import com.example.customerms.domain.Customer;
import com.example.customerms.dto.verification.ConfirmRequestDto;
import com.example.customerms.dto.verification.ConfirmResponseDto;
import com.example.customerms.response.BaseResponse;
import com.example.customerms.service.OtpPublisherService;
import com.example.customerms.service.UserConfirmationService;
import com.example.customerms.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verification/provider")
@RequiredArgsConstructor
public class VerificationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final OtpPublisherService otpPublisherService;
    private final UserConfirmationService userConfirmationService;


    @PostMapping("/send-sms-otp")
    public BaseResponse<Void> sendSmsOtp
            (@RequestHeader("Authorization") String authHeader) {
        Customer user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishPhoneOtp(user);
        return BaseResponse.success();
    }

    @PostMapping("/confirm-sms-otp")
    public BaseResponse<ConfirmResponseDto> verifySmsOtp
            (@RequestHeader("Authorization") String authHeader,
             @RequestBody ConfirmRequestDto confirmRequestDto) {
        Customer user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        return BaseResponse
                .success(userConfirmationService.confirmPhone(user, confirmRequestDto));
    }

    @PostMapping("/send-email-otp")
    public BaseResponse<Void> sendEmailOtp
            (@RequestHeader("Authorization") String authHeader) {
        Customer user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishEmailOtp(user);
        return BaseResponse.success();
    }
}