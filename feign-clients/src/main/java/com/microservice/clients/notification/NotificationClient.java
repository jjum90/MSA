package com.microservice.clients.notification;

import com.microservice.clients.notification.dto.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification")
public interface NotificationClient {
    @PostMapping("api/v1/notification")
    public void sendNotification(@RequestBody NotificationDto.Request notificationRequest);
}
