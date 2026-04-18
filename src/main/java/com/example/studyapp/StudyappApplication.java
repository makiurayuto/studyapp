package com.example.studyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.studyapp")
public class StudyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyappApplication.class, args);
	}

}
