package com.codecentric.hystrix.warstories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.context.annotation.Import;
import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusConfiguration;

/**
 * @author Benjamin Wilms (xd98870)
 */
@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrixDashboard
@Import(ArchaiusConfiguration.class)
public class TransportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportServiceApplication.class, args);
    }
}
