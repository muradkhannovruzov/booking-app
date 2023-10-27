package com.example.customerms.service.impl;

import com.example.customerms.domain.Customer;
import com.example.customerms.domain.enums.VerificationType;
import com.example.customerms.dto.otp.OtpInfoDto;
import com.example.customerms.service.KafkaMsgPublisher;
import com.example.customerms.service.OtpPublisherService;
import com.example.customerms.service.VerificationOtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OtpPublisherServiceImpl implements OtpPublisherService {

    private final VerificationOtpService otpService;
    private final KafkaMsgPublisher kafkaMsgPublisher;

    @Value("${spring.kafka.topics.sms-otp}")
    private String SMS_OTP_TOPIC;
    @Value("${spring.kafka.topics.email-otp}")
    private String EMAIL_OTP_TOPIC;

    @Override
    public void publishPhoneOtp(Customer customer) {
        var verificationOtp = otpService.createForUser(customer, VerificationType.PHONE);

        OtpInfoDto otpDto = new OtpInfoDto();
        otpDto.setTarget(customer.getPhone());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(customer.getId(), otpDto, SMS_OTP_TOPIC);
    }

    @Override
    public void publishEmailOtp(Customer customer) {
        var verificationOtp = otpService.createForUser(customer, VerificationType.EMAIL);

        OtpInfoDto otpDto = new OtpInfoDto();
        otpDto.setTarget(customer.getEmail());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(customer.getId(), otpDto, EMAIL_OTP_TOPIC);

    }
}
