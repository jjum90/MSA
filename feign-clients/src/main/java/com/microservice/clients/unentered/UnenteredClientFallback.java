package com.microservice.clients.unentered;

import com.microservice.clients.unentered.dto.UnenteredCheckHistoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UnenteredClientFallback implements UnenteredClient{
    @Override
    public UnenteredCheckHistoryDto.Response isUnentered(Integer memberId) {
        log.info("fallback unenteredClient");
        return UnenteredCheckHistoryDto.Response.builder().isUnentered(false).build();
    }
}
