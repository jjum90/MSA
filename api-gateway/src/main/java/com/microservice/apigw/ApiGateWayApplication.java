package com.microservice.apigw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * APIGATEWAY
 * 프록시의 역할과 로드밸런싱
 * 인증 및 로깅 서버로서의 기능
 * 클라이언트로부터 모든 요청을 받아 인증, 로깅 절차를 거칠 수 있고 모든 마이크로서비스를 호출 가능
 */
@SpringBootApplication
@EnableEurekaClient
public class ApiGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class, args);
    }
}
