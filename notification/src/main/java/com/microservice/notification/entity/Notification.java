package com.microservice.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @SequenceGenerator(name = "notification_sequence_id", sequenceName = "notification_sequence_id", initialValue = 1, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_sequence_id")
    private Integer id;
    private Integer memberId;
    private String sender;
    private String message;
    private LocalDateTime sendAt;
}
