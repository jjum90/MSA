package com.microservice.unentered;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UnenteredApplication {
    public static void main(String[] args) {
        SpringApplication.run(UnenteredApplication.class, args);
    }
}
