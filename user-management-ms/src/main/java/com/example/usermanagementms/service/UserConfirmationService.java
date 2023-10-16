package com.example.usermanagementms.service;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.dto.verification.ConfirmRequestDto;
import com.example.usermanagementms.dto.verification.ConfirmResponseDto;

public interface UserConfirmationService {
    ConfirmResponseDto confirmPhone(User user, ConfirmRequestDto confirmOtpRequest);
    ConfirmResponseDto confirmEmail(User user, ConfirmRequestDto confirmOtpRequest);
}
