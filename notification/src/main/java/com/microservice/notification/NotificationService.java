package com.microservice.notification;

import com.microservice.notification.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationDto.Request notificationRequest) {
        notificationRepository.save(
                notificationRequest.toEntity()
        );
        log.info("New notification is saved");
    }
}
