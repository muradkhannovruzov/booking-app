package com.example.providerms.controller;

import com.example.providerms.service.BreakScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/break-schedule")
@RequiredArgsConstructor
public class BreakScheduleController {
    private final BreakScheduleService breakScheduleService;


}
