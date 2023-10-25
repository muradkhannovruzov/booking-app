package com.example.servicems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMsApplication.class, args);
    }

}
