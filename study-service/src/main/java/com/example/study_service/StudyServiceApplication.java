package com.example.study_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyServiceApplication.class, args);
	}

}
