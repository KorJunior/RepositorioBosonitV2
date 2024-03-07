package com.example.block16springcloud.rabbit_mq.publisher;

import org.example.dto.trip.output.TripOutputComplete;
import org.example.dto.tripShop.TripShopOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routingKey;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);
    private RabbitTemplate rabbitTemplate;
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    public void sendMessage(TripShopOutput message) {
        LOGGER.info(String.format("Producing message: %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
