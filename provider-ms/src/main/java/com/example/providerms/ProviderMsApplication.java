package com.example.providerms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ProviderMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProviderMsApplication.class, args);
    }

}
