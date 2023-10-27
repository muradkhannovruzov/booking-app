package com.example.customerms.service;

import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

public interface KafkaMsgPublisher {
    CompletableFuture<SendResult<Long, Object>> publish(Long key, Object value, String topic);
}
