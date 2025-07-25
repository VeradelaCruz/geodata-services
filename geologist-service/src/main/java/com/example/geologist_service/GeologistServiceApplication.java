package com.example.geologist_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GeologistServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeologistServiceApplication.class, args);
	}

}
