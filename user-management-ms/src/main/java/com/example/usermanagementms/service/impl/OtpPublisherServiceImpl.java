package com.example.usermanagementms.service.impl;

import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.domain.enums.VerificationType;
import com.example.usermanagementms.dto.otp.SendOtpDto;
import com.example.usermanagementms.service.KafkaMsgPublisher;
import com.example.usermanagementms.service.OtpPublisherService;
import com.example.usermanagementms.service.VerificationOtpService;
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
    public void publishPhoneOtp(User user) {
        var verificationOtp = otpService.createForUser(user, VerificationType.PHONE);

        SendOtpDto otpDto = new SendOtpDto();
        otpDto.setTarget(user.getPhone());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(user.getId(), otpDto, SMS_OTP_TOPIC);
    }

    @Override
    public void publishEmailOtp(User user) {
        var verificationOtp = otpService.createForUser(user, VerificationType.EMAIL);

        SendOtpDto otpDto = new SendOtpDto();
        otpDto.setTarget(user.getEmail());
        otpDto.setOtp(verificationOtp.getOtp());

        kafkaMsgPublisher.publish(user.getId(), otpDto, EMAIL_OTP_TOPIC);

    }
}
