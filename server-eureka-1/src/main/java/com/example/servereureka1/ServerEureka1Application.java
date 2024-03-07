package com.example.servereureka1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ServerEureka1Application {

	public static void main(String[] args) {
		SpringApplication.run(ServerEureka1Application.class, args);
	}

}
