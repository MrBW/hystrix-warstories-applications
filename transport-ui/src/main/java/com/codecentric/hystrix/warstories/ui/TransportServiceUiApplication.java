package com.codecentric.hystrix.warstories.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.configuration.ArchaiusConfiguration;
import com.codecentric.hystrix.warstories.ui.service.ConnoteService;

@SpringBootApplication
@Import(ArchaiusConfiguration.class)
public class TransportServiceUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportServiceUiApplication.class, args);
    }

    @Bean
    public ConnoteService connoteService() {
        return new ConnoteService(new RestTemplate());
    }

}
