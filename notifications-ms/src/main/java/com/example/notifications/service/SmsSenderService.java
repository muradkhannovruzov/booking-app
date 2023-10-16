package com.example.notifications.service;

public interface SmsSenderService {
    void sendSms(String toNumber, String text);
}
