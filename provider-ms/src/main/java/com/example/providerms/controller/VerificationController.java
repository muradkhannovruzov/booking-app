package com.example.providerms.controller;

import com.example.providerms.domain.Provider;
import com.example.providerms.dto.verification.ConfirmRequestDto;
import com.example.providerms.dto.verification.ConfirmResponseDto;
import com.example.providerms.response.BaseResponse;
import com.example.providerms.service.OtpPublisherService;
import com.example.providerms.service.UserConfirmationService;
import com.example.providerms.utils.JwtTokenUtil;
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
        Provider user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishPhoneOtp(user);
        return BaseResponse.success();
    }

    @PostMapping("/confirm-sms-otp")
    public BaseResponse<ConfirmResponseDto> verifySmsOtp
            (@RequestHeader("Authorization") String authHeader,
             @RequestBody ConfirmRequestDto confirmRequestDto) {
        Provider user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        return BaseResponse
                .success(userConfirmationService.confirmPhone(user, confirmRequestDto));
    }

    @PostMapping("/send-email-otp")
    public BaseResponse<Void> sendEmailOtp
            (@RequestHeader("Authorization") String authHeader) {
        Provider user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishEmailOtp(user);
        return BaseResponse.success();
    }
}