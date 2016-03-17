package com.codecentric.hystrix.warstories;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusConfiguration;

@SpringBootApplication
@Import(ArchaiusConfiguration.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}
