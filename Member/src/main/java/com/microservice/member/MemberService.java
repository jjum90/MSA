package com.microservice.member;

import com.microservice.member.dto.MemberDto;
import com.microservice.member.dto.UnenteredCheckHistoryDto;
import com.microservice.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;
    public void register(MemberDto.Request memberRequest) {
        Member member = memberRequest.toEntity();
        memberRepository.saveAndFlush(member);

        // check unentered member

        // Sending notifications using restTemplate
        UnenteredCheckHistoryDto.Response response = restTemplate.getForObject("http://UNENTERED/api/v1/unentered/{memberId}",
                UnenteredCheckHistoryDto.Response.class,
                member.getId());

        if(response.isUnentered()) {
            throw new IllegalStateException("not join member");
        }
    }
}
