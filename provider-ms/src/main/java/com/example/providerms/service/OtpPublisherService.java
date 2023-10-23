package com.example.providerms.service;


import com.example.providerms.domain.Provider;

public interface OtpPublisherService {
    void publishPhoneOtp(Provider user);

    void publishEmailOtp(Provider user);
}
