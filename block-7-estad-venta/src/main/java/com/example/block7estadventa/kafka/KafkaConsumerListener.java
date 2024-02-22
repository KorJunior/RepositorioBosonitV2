package com.example.block7estadventa.kafka;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class KafkaConsumerListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    private Map<String, Object> lastReceivedMessage;

    @KafkaListener(topics = {"factura"}, groupId = "group_id")
    public void listen(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            lastReceivedMessage = objectMapper.readValue(message, new TypeReference<Map<String, Object>>() {});

            logger.info("Mensaje Kafka recibido: {}", lastReceivedMessage);
        } catch (JsonProcessingException e) {
            logger.error("Error al procesar el mensaje Kafka: {}", e.getMessage());
        }
    }
}