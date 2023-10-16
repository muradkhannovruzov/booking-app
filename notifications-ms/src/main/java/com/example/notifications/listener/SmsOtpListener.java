package com.example.notifications.listener;

import com.example.notifications.dto.OtpInfoDto;
import com.example.notifications.service.SmsSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SmsOtpListener {

    private final SmsSenderService smsSenderService;

    @KafkaListener(
            id = "1",
            topics = "sms-otp-notification-topic",
            groupId = "notification-group-id",
            containerFactory = "kafkaJsonListenerContainerFactory"
    )
    public void listenForSmsOtp(OtpInfoDto otpInfoDto) {
        System.out.println("SMS OTP RECEIVED: " + otpInfoDto);
        smsSenderService.sendSms(otpInfoDto.getTarget(), otpInfoDto.getOtp());
    }
}
