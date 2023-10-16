package com.example.usermanagementms;

import com.example.usermanagementms.domain.Role;
import com.example.usermanagementms.domain.User;
import com.example.usermanagementms.repository.UserRepository;
import com.example.usermanagementms.service.OtpPublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@RequiredArgsConstructor
public class UserManagementMsApplication implements CommandLineRunner {
	private final OtpPublisherService otpPublisherService;
	private final UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(Role.PROVIDER.getAuthorities());
//		User user = userRepository.findByUsername("muradxan").get();
//		otpPublisherService.publishPhoneOtp(user);
//		otpPublisherService.publishPhoneOtp(user);
//		otpPublisherService.publishPhoneOtp(user);
//		otpPublisherService.publishPhoneOtp(user);
	}
}
