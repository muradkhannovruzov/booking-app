package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.VerificationOtp;
import com.example.usermanagementms.domain.enums.VerificationType;
import com.example.usermanagementms.dto.verification.ConfirmRequestDto;
import com.example.usermanagementms.dto.verification.ConfirmResponseDto;
import com.example.usermanagementms.repository.UserRepository;
import com.example.usermanagementms.service.UserConfirmationService;
import com.example.usermanagementms.service.VerificationOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConfirmationServiceWithOtp implements UserConfirmationService {

    private final VerificationOtpService verificationOtpService;
    private final UserRepository userRepository;

    @Override
    public ConfirmResponseDto confirmPhone(User user, ConfirmRequestDto requestDto) {
        VerificationOtp verificationOtp =
                verificationOtpService.getLatestOtpByUserIdAndType(user.getId(), VerificationType.PHONE);

        if (isOtpExpired(verificationOtp)) {
            return buildResponse(false, "OTP is expired");
        }

        if (isOtpCorrect(verificationOtp, requestDto)) {
            activateUser(user);
            return buildResponse(true, "Phone number is verified");
        }

        return buildResponse(false, "OTP is not correct");
    }

    @Override
    public ConfirmResponseDto confirmEmail(User user, ConfirmRequestDto requestDto) {
        VerificationOtp verificationOtp =
                verificationOtpService.getLatestOtpByUserIdAndType(user.getId(), VerificationType.EMAIL);

        if (isOtpExpired(verificationOtp)) {
            return buildResponse(false, "OTP is expired");
        }

        if (isOtpCorrect(verificationOtp, requestDto)) {
            activateUser(user);
            return buildResponse(true, "Email is verified");
        }

        return buildResponse(false, "OTP is not correct");
    }



    private boolean isOtpExpired(VerificationOtp verificationOtp) {
        return verificationOtpService.isOtpExpired(verificationOtp);
    }

    private boolean isOtpCorrect(VerificationOtp verificationOtp, ConfirmRequestDto requestDto) {
        return verificationOtp.getOtp().equals(requestDto.getConfirmationData());
    }

    private void activateUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }

    private ConfirmResponseDto buildResponse(boolean isCorrect, String message) {
        return ConfirmResponseDto.builder()
                .isCorrect(isCorrect)
                .message(message)
                .build();
    }
}
