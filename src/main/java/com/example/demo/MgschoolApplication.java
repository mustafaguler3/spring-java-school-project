package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.demo.repository")
@EntityScan("com.example.demo.domain")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableFeignClients(basePackages = "com.example.demo.proxy")
public class MgschoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(MgschoolApplication.class, args);
	}

}
