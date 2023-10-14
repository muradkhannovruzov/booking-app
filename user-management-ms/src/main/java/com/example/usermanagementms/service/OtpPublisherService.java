package com.example.usermanagementms.service;

import com.example.usermanagementms.domain.User;

public interface OtpPublisherService {
    void publishPhoneOtp(User user);

    void publishEmailOtp(User user);
}
