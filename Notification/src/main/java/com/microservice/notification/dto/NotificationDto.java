package com.microservice.notification.dto;

import com.microservice.notification.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDateTime;

public class NotificationDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Info {
        private Integer id;
        private Integer memberId;
        private String sender;
        private String message;
        private LocalDateTime sendAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private Integer memberId;
        private String sender;
        private String message;

        public Notification toEntity() {
            return Notification.builder()
                    .memberId(memberId)
                    .sender(sender)
                    .message(message)
                    .build();
        }
    }
}
