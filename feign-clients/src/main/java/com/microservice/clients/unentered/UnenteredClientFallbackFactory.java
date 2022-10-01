package com.microservice.clients.unentered;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UnenteredClientFallbackFactory implements FallbackFactory<UnenteredClient> {

    private final UnenteredClientFallback fallback;

    @Override
    public UnenteredClient create(Throwable cause) {
        log.info("Error message: {}", cause.getMessage());
        return fallback;
    }
}
