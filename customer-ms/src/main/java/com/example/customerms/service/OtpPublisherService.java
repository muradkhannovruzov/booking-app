package com.example.customerms.service;


import com.example.customerms.domain.Customer;

public interface OtpPublisherService {
    void publishPhoneOtp(Customer provider);

    void publishEmailOtp(Customer provider);
}
