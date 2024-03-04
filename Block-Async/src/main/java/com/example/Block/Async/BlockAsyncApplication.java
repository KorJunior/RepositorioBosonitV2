package com.example.Block.Async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BlockAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlockAsyncApplication.class, args);
	}

}
