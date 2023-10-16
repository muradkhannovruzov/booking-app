package com.example.usermanagementms.controller;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.dto.verification.ConfirmRequestDto;
import com.example.usermanagementms.dto.verification.ConfirmResponseDto;
import com.example.usermanagementms.response.BaseResponse;
import com.example.usermanagementms.service.OtpPublisherService;
import com.example.usermanagementms.service.UserConfirmationService;
import com.example.usermanagementms.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final JwtTokenUtil jwtTokenUtil;
    private final OtpPublisherService otpPublisherService;
    private final UserConfirmationService userConfirmationService;


    @PostMapping("/send-sms-otp")
    public BaseResponse<Void> sendSmsOtp
            (@RequestHeader("Authorization") String authHeader) {
        User user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishPhoneOtp(user);
        return BaseResponse.success();
    }

    @PostMapping("/confirm-sms-otp")
    public BaseResponse<ConfirmResponseDto> verifySmsOtp
            (@RequestHeader("Authorization") String authHeader,
             @RequestBody ConfirmRequestDto confirmRequestDto) {
        User user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        return BaseResponse
                .success(userConfirmationService.confirmPhone(user, confirmRequestDto));
    }

    @PostMapping("/send-email-otp")
    public BaseResponse<Void> sendEmailOtp
            (@RequestHeader("Authorization") String authHeader) {
        User user = jwtTokenUtil.extractUserFromAuthHeader(authHeader);
        otpPublisherService.publishEmailOtp(user);
        return BaseResponse.success();
    }
}