package com.example.servicecatalogms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceCatalogMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCatalogMsApplication.class, args);
    }

}
