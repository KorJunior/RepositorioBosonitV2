package com.example.block7jpaconrelacionesyllamadasentremicros;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication

public class Block7JpaConRelacionesYLlamadasEntreMicrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block7JpaConRelacionesYLlamadasEntreMicrosApplication.class, args);
	}


}
