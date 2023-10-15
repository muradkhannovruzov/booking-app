package com.example.notifications.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailOtpListener {

    @KafkaListener(
            id = "2",
            topics = "email-otp-notification-topic",
            groupId = "notification-group-id",
            containerFactory = "kafkaJsonListenerContainerFactory"
    )
    public void listenForEmailOtp(String otpInfoDto) {
        System.out.println("EMAIL OTP RECEIVED: " + otpInfoDto);
    }
}
