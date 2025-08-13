package com.example.geologist_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.example.geologist_service.repository")
public class GeologistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeologistServiceApplication.class, args);
	}

}
