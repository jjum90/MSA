package com.microservice.clients.unentered;

import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.String.format;
import static java.time.temporal.ChronoField.INSTANT_SECONDS;

public class UnenteredClientRetryConfig {
    /**
     * Retryer 는 1초 를 시작으로 1.5 곱하면서 재시도를 하고, 최대 3번 합니다.
     * @return
     */
    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 2000, 3);
    }

    @Bean
    public ErrorDecoder decoder() {
        return (s, response) -> {
            if(response.status() > 400) {
                OffsetDateTime offsetDateTime = OffsetDateTime.now().plusSeconds(1L);
                Date retryAfter = new Date(TimeUnit.SECONDS.toMillis(offsetDateTime.getLong(INSTANT_SECONDS)));
                return new RetryableException(response.status(), format("요청에 실패했습니다. 재시도 합니다."), response.request().httpMethod(), retryAfter, response.request());
            }
            return new IllegalStateException(format("%s, status: %s, header: %s, 요청 실패", s, response.status(), response.headers()));
        };
    }
}
