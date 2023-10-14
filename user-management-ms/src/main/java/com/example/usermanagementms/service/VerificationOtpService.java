package com.example.usermanagementms.service;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.VerificationOtp;
import com.example.usermanagementms.domain.enums.VerificationType;

public interface VerificationOtpService {
    VerificationOtp createForUser(User user, VerificationType verificationType);
}
