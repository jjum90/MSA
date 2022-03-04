package com.microservice.unentered.dto;

import lombok.*;

import java.time.LocalDateTime;


public class UnenteredCheckHistoryDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Info {
        private Integer id;
        private Integer memberId;
        private boolean isUnentered;
        private LocalDateTime createdAt;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private boolean isUnentered;
    }
}
