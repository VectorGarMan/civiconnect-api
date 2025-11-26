package com.vectorgarman.civiconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// netstat -ano | findstr :8080
// taskkill /PID ##### /F

// Swagger - http://localhost:8080/swagger-ui/index.html#

@SpringBootApplication
public class CiviconnectApplication {
	public static void main(String[] args) {
		SpringApplication.run(CiviconnectApplication.class, args);
	}
}