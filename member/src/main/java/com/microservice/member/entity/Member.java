package com.microservice.member.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/**
 * 회원 가입 정보
 */
public class Member {
    @Id
    @SequenceGenerator(name = "member_id_sequence", sequenceName = "member_id_sequence", initialValue = 1, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_sequence")
    private Integer id;
    private String email;
    private String lastName;
    private String firstName;
    private LocalDateTime createdAt;
}
