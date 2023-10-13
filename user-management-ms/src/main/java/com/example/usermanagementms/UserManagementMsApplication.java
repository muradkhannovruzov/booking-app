package com.example.usermanagementms;

import com.example.usermanagementms.utils.OTPUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserManagementMsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementMsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 10; i++) {
			System.out.println(OTPUtils.generate());
		}
	}
}
