package com.backend.futScore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FutScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(FutScoreApplication.class, args);
	}

}
