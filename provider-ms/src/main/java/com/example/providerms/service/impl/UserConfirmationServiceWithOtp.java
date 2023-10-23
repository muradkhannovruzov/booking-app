package com.example.providerms.service.impl;

import com.example.providerms.domain.Provider;
import com.example.providerms.domain.VerificationOtp;
import com.example.providerms.domain.enums.VerificationType;
import com.example.providerms.dto.verification.ConfirmRequestDto;
import com.example.providerms.dto.verification.ConfirmResponseDto;
import com.example.providerms.repository.ProviderRepository;
import com.example.providerms.service.UserConfirmationService;
import com.example.providerms.service.VerificationOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserConfirmationServiceWithOtp implements UserConfirmationService {

    private final VerificationOtpService verificationOtpService;
    private final ProviderRepository providerRepository;

    @Override
    public ConfirmResponseDto confirmPhone(Provider user, ConfirmRequestDto requestDto) {
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
    public ConfirmResponseDto confirmEmail(Provider user, ConfirmRequestDto requestDto) {
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

    private void activateProvider(Provider user) {
        user.setEnabled(true);
        providerRepository.save(user);
    }

    private ConfirmResponseDto buildConfirmResponse(boolean isCorrect, String message) {
        return ConfirmResponseDto.builder()
                .isCorrect(isCorrect)
                .message(message)
                .build();
    }
}
