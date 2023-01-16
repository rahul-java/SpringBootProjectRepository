package com.app.rating.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class SbMicroservicesRatingServiceApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(SbMicroservicesRatingServiceApplication.class, args);
	}

}
