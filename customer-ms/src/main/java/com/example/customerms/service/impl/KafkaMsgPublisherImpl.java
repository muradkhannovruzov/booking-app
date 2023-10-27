package com.example.customerms.service.impl;

import com.example.customerms.service.KafkaMsgPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMsgPublisherImpl implements KafkaMsgPublisher {

    private final KafkaTemplate<Long, Object> kafkaTemplate;

    @Override
    public CompletableFuture<SendResult<Long, Object>> publish(Long key, Object value, String topic) {
        var future = kafkaTemplate.send(topic, key, value);

        future.whenComplete((result, exception) -> {
            if(exception != null){
                log.error(exception.getMessage());
                future.completeExceptionally(exception);
            } else {
                handleSuccess(key, value.toString(), result);
                future.complete(result);
            }
        });

        return future;
    }

    private void handleSuccess(Long key, Object value, SendResult<Long, Object> result) {
        log.info("Message Sent Successfully for the key : {} and the value is {} , partition is {}",
                key, value, result.getRecordMetadata().partition());
    }
}
