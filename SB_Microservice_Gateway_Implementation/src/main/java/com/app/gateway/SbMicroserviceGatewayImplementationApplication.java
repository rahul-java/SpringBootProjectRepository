package com.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SbMicroserviceGatewayImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbMicroserviceGatewayImplementationApplication.class, args);
	}

}
