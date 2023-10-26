package com.example.servicecatalogms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/service")
public class ServiceController {

        @GetMapping("/hello")
        public String hello() {
            return "Hello from service-ms";
        }
}
