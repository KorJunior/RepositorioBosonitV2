package com.example.block7jpaconrelacionesyllamadasentremicros.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.example.dto.dtoProducto.productoOutPutDto.ProductoOutPutHistorico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

import static com.example.block7jpaconrelacionesyllamadasentremicros.kafka.KafkaClienteConfig.getStringObjectMap;

@Configuration
public class KafkaProductoConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boootstrapServers;

    public Map<String, Object> producerConfig(){
        return getStringObjectMap(boootstrapServers);

    }

    @Bean
    public ProducerFactory<String, ProductoOutPutHistorico> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }

    @Bean
    public KafkaTemplate<String, ProductoOutPutHistorico> productoKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
