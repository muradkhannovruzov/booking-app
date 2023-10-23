package com.example.providerms.service;

import com.example.providerms.domain.Provider;
import com.example.providerms.dto.verification.ConfirmRequestDto;
import com.example.providerms.dto.verification.ConfirmResponseDto;

public interface UserConfirmationService {
    ConfirmResponseDto confirmPhone(Provider user, ConfirmRequestDto confirmOtpRequest);
    ConfirmResponseDto confirmEmail(Provider user, ConfirmRequestDto confirmOtpRequest);
}
