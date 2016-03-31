package com.codecentric.hystrix.warstories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Import;

import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusConfiguration;

@SpringBootApplication
@EnableCircuitBreaker
@Import(ArchaiusConfiguration.class)
public class ConnoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConnoteServiceApplication.class, args);
    }
}
