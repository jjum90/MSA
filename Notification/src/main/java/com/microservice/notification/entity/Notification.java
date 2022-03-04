package com.microservice.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @SequenceGenerator(name = "notification_sequence_id", sequenceName = "notification_sequence_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence_id")
    private Integer id;
    private Integer memberId;
    private String sender;
    private String message;
    private LocalDateTime sendAt;
}
