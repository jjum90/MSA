package com.microservice.unentered;

import com.microservice.unentered.dto.UnenteredCheckHistoryDto;
import com.microservice.unentered.entity.UnenteredCheckHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/unentered")
@Slf4j
public class UnenteredController {
    private final UnenteredService unenteredService;

    @GetMapping(path = "{memberId}")
    public UnenteredCheckHistoryDto.Response isUnentered(@PathVariable("memberId") Integer memberId){
        log.info("check for unentered member {}", memberId);
        boolean isUnentered = unenteredService.isUnenteredMember(memberId);
        return new UnenteredCheckHistoryDto.Response(isUnentered);
    }
}
