package com.microservice.amqp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
/**
 * Producer 생성 class
 */
public class RabbitMQProducer {
    private final AmqpTemplate amqpTemplate;

    public void publish(String exchange, String routingKey, Object payload) {
        log.info("Publishing to {} using routingkey {}. Payload : {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        log.info("Published to {} using routingkey {}. Payload : {}", exchange, routingKey, payload);
    }
}
