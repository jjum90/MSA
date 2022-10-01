package com.microservice.clients.unentered;

import com.microservice.clients.unentered.dto.UnenteredCheckHistoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "unentered", fallbackFactory = UnenteredClientFallbackFactory.class, configuration = {UnenteredClientRetryConfig.class})
public interface UnenteredClient {
    @GetMapping(path = "api/v1/unentered/{memberId}")
    UnenteredCheckHistoryDto.Response isUnentered(@PathVariable("memberId") Integer memberId);
}
