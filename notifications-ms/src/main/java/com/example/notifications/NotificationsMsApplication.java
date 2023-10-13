package com.example.notifications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NotificationsMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationsMsApplication.class, args);
    }

}
