package com.codecentric.hystrix.warstories.ui.service;

import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class TransportService {

    private RestTemplate restTemplate;

    public TransportService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public ConnoteDTO getConnote() {
        // return restTemplate.getForObject("http://transport/create", Tra.class);
        return null;
    }
}
