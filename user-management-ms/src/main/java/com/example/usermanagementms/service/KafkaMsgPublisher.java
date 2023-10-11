package com.example.usermanagementms.service;

import org.springframework.kafka.support.SendResult;
import java.util.concurrent.CompletableFuture;

public interface KafkaMsgPublisher {

    // todo: fix deprecated: ListenableFuture
    CompletableFuture<SendResult<Long, Object>> publish(Long key, Object value, String topic);
}
