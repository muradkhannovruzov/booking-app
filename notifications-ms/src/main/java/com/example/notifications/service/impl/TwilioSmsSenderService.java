package com.example.notifications.service.impl;

import com.example.notifications.service.SmsSenderService;
import com.twilio.Twilio;
import org.springframework.stereotype.Service;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class TwilioSmsSenderService implements SmsSenderService {

    private final String ACCOUNT_SID = "AC06b9a784da8b30fd9d09ee3b04eb39b9";
    private final String AUTH_TOKEN = "6fc556b52c2cd1c5a71c99606549d0d3";
    private final String TWILIO_NUMBER = "+1 904 337 5774";



    @Override
    public void sendSms(String toNumber, String text) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber(toNumber),
                        new PhoneNumber(TWILIO_NUMBER),
                        text
                )
                .create();

        System.out.println(message.getSid() + " " + message.getStatus());

    }
}
