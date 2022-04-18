package com.microservice.member.dto;

import com.microservice.member.entity.Member;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MemberDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Info {
        private Integer id;
        private String email;
        private String lastName;
        private String firstName;
        private LocalDateTime createAt;

    }
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        @Email
        private String email;
        @NotBlank
        private String lastName;
        @NotBlank
        private String firstName;

        public Member toEntity(){
            return Member.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .createdAt(LocalDateTime.now())
                    .build();
        }
    }
}
