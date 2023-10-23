package com.example.providerms.service;


import com.example.providerms.domain.Provider;

public interface OtpPublisherService {
    void publishPhoneOtp(Provider provider);

    void publishEmailOtp(Provider provider);
}
