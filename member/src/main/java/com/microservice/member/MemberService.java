package com.microservice.member;

import com.microservice.amqp.RabbitMQProducer;
import com.microservice.clients.notification.NotificationClient;
import com.microservice.clients.notification.dto.NotificationDto;
import com.microservice.clients.unentered.UnenteredClient;
import com.microservice.clients.unentered.dto.UnenteredCheckHistoryDto;
import com.microservice.member.dto.MemberDto;
import com.microservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final UnenteredClient unenteredClient;
    private final RabbitMQProducer rabbitMQProducer;
//    private final NotificationClient notificationClient;
    public void register(MemberDto.Request memberRequest) {
        Member member = memberRequest.toEntity();
        memberRepository.saveAndFlush(member);

        // check unentered member
        UnenteredCheckHistoryDto.Response response = unenteredClient.isUnentered(member.getId());
        if(response.isUnentered()) {
            throw new IllegalStateException("not join member");
        }
        NotificationDto.Request notificationRequest = NotificationDto.Request
                .builder()
                .memberId(member.getId())
                .message("Thank you join us!!!")
                .sender("seok")
                .build();
        // Send notification
        rabbitMQProducer.publish("internal.exchange", "internal.notification.routing-key", notificationRequest);
//        notificationClient.sendNotification(NotificationDto.Request
//                .builder()
//                .memberId(member.getId())
//                .message("Thank you join us!!!")
//                .sender("seok")
//                .build()
//        );


    }
}
