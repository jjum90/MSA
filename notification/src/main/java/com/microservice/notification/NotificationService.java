package com.microservice.notification;

import com.microservice.clients.notification.dto.NotificationDto;
import com.microservice.notification.entity.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public void send(NotificationDto.Request notificationRequest) {
        notificationRepository.save(
            Notification.builder()
                    .memberId(notificationRequest.getMemberId())
                    .sender(notificationRequest.getSender())
                    .message(notificationRequest.getMessage())
                    .sendAt(LocalDateTime.now())
                    .build()
        );
        log.info("New notification is saved");
    }
}
