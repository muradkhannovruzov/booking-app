package com.example.providerms.service.impl;

import com.example.providerms.domain.Provider;
import com.example.providerms.domain.enums.VerificationType;
import com.example.providerms.dto.otp.OtpInfoDto;
import com.example.providerms.service.KafkaMsgPublisher;
import com.example.providerms.service.OtpPublisherService;
import com.example.providerms.service.VerificationOtpService;
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
    public void publishPhoneOtp(Provider provider) {
        var verificationOtp = otpService.createForUser(provider, VerificationType.PHONE);

        OtpInfoDto otpDto = new OtpInfoDto();
        otpDto.setTarget(provider.getPhone());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(provider.getId(), otpDto, SMS_OTP_TOPIC);
    }

    @Override
    public void publishEmailOtp(Provider provider) {
        var verificationOtp = otpService.createForUser(provider, VerificationType.EMAIL);

        OtpInfoDto otpDto = new OtpInfoDto();
        otpDto.setTarget(provider.getEmail());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(provider.getId(), otpDto, EMAIL_OTP_TOPIC);

    }
}
