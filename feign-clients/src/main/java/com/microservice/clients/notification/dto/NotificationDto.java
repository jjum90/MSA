package com.microservice.clients.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    }
}
