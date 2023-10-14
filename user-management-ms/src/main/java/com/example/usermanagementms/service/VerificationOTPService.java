package com.example.usermanagementms.service;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.VerificationOTP;
import com.example.usermanagementms.domain.enums.VerificationType;

public interface VerificationOTPService {
    VerificationOTP createForUser(User user, VerificationType verificationType);
}
