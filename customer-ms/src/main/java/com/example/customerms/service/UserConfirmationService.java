package com.example.customerms.service;

import com.example.customerms.domain.Customer;
import com.example.customerms.dto.verification.ConfirmRequestDto;
import com.example.customerms.dto.verification.ConfirmResponseDto;

public interface UserConfirmationService {
    ConfirmResponseDto confirmPhone(Customer user, ConfirmRequestDto confirmOtpRequest);
    ConfirmResponseDto confirmEmail(Customer user, ConfirmRequestDto confirmOtpRequest);
}
