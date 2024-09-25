package com.managementbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ManagementBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementBackendApplication.class, args);
	}

}
