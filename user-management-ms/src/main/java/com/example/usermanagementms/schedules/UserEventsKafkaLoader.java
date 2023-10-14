package com.example.usermanagementms.schedules;

import com.example.usermanagementms.config.JwtService;
import com.example.usermanagementms.repository.UserRepository;
import com.example.usermanagementms.service.KafkaMsgPublisher;
import com.example.usermanagementms.service.VerificationOTPService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventsKafkaLoader {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final VerificationOTPService verificationOTPService;
    private final KafkaMsgPublisher kafkaMsgPublisher;
    private final ModelMapper modelMapper;
}
