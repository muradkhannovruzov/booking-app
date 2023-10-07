package com.example.usermanagementms.controller;

import com.example.usermanagementms.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    @GetMapping
    public BaseResponse<String> sayHello() {
        return BaseResponse.success("demo-controller");
    }
}
