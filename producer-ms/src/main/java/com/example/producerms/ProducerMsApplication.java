package com.example.producerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class ProducerMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProducerMsApplication.class, args);
    }

}
