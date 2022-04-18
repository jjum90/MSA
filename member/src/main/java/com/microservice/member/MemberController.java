package com.microservice.member;

import com.microservice.member.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/members")
@Slf4j
/**
 * 멤버 REST API
 */
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    public void register(@RequestBody @Valid MemberDto.Request memberRequest) {
        log.info("new member registraion {} ", memberRequest);
        memberService.register(memberRequest);
    }
}
