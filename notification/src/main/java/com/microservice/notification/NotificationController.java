package com.microservice.notification;

import com.microservice.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {
    private final NotificationService notificationService;
    @PostMapping
    public void sendNotification(@RequestBody NotificationDto.Request notificationRequest) {
        notificationService.send(notificationRequest);
    }
}
