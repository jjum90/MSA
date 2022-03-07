package com.microservice.notification;

import com.microservice.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationDto.Request notificationRequest) {
        notificationRepository.save(
                notificationRequest.toEntity()
        );
    }
}
