package com.microservice.notification.rabbitmq;

import com.microservice.clients.notification.dto.NotificationDto;
import com.microservice.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationDto.Request notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }
}
