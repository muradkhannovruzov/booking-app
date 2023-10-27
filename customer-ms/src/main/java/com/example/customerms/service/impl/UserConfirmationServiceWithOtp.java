package com.example.customerms.service.impl;

import com.example.customerms.domain.Customer;
import com.example.customerms.domain.VerificationOtp;
import com.example.customerms.domain.enums.VerificationType;
import com.example.customerms.dto.verification.ConfirmRequestDto;
import com.example.customerms.dto.verification.ConfirmResponseDto;
import com.example.customerms.repository.CustomerRepository;
import com.example.customerms.service.UserConfirmationService;
import com.example.customerms.service.VerificationOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConfirmationServiceWithOtp implements UserConfirmationService {

    private final VerificationOtpService verificationOtpService;
    private final CustomerRepository customerRepository;

    @Override
    public ConfirmResponseDto confirmPhone(Customer user, ConfirmRequestDto requestDto) {
        VerificationOtp verificationOtp =
                verificationOtpService.getLatestOtpByUserIdAndType(user.getId(), VerificationType.PHONE);

        if (isOtpExpired(verificationOtp)) {
            return buildConfirmResponse(false, "OTP is expired");
        }

        if (isOtpCorrect(verificationOtp, requestDto)) {
            activateProvider(user);
            return buildConfirmResponse(true, "Phone number is verified");
        }

        return buildConfirmResponse(false, "OTP is not correct");
    }

    @Override
    public ConfirmResponseDto confirmEmail(Customer user, ConfirmRequestDto requestDto) {
        VerificationOtp verificationOtp =
                verificationOtpService.getLatestOtpByUserIdAndType(user.getId(), VerificationType.EMAIL);

        if (isOtpExpired(verificationOtp)) {
            return buildConfirmResponse(false, "OTP is expired");
        }

        if (isOtpCorrect(verificationOtp, requestDto)) {
            activateProvider(user);
            return buildConfirmResponse(true, "Email is verified");
        }

        return buildConfirmResponse(false, "OTP is not correct");
    }



    private boolean isOtpExpired(VerificationOtp verificationOtp) {
        return verificationOtpService.isOtpExpired(verificationOtp);
    }

    private boolean isOtpCorrect(VerificationOtp verificationOtp, ConfirmRequestDto requestDto) {
        return verificationOtp.getOtp().equals(requestDto.getConfirmationData());
    }

    private void activateProvider(Customer user) {
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        customerRepository.save(user);
    }

    private ConfirmResponseDto buildConfirmResponse(boolean isCorrect, String message) {
        return ConfirmResponseDto.builder()
                .isCorrect(isCorrect)
                .message(message)
                .build();
    }
}
