package com.microservice.unentered.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UnenteredCheckHistory {
    @Id
    @SequenceGenerator(name = "unentered_id_sequence", sequenceName = "unentered_id_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unentered_id_sequence")
    private Integer id;
    private Integer memberId;
    private boolean isUnentered;
    private LocalDateTime createdAt;
}
