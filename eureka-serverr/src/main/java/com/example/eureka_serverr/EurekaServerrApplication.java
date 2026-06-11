package com.example.eureka_serverr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerrApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerrApplication.class, args);
	}

}
