package com.example.notifications.listener;

import com.example.notifications.dto.OtpInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsOtpListener {
    @KafkaListener(
            id = "1",
            topics = "sms-otp-notification-topic",
            groupId = "notification-group-id",
            containerFactory = "kafkaJsonListenerContainerFactory"
    )
    public void listenForSmsOtp(OtpInfoDto otpInfoDto) {
        System.out.println("SMS OTP RECEIVED: " + otpInfoDto);
    }
}
