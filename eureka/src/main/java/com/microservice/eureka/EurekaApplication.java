package com.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka Server
 * MSA 인스턴스 정보를 등록 및 조회하기 위한 서버
 * 참조 : https://sup2is.github.io/2020/04/07/spring-cloud-eureka-with-netfix-feign-client-example.html
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication.class, args);
    }
}
