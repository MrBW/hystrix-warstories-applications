package com.codecentric.hystrix.warstories.ui.service;

import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ConnoteService {

    private RestTemplate restTemplate;

    public ConnoteService(RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    public ConnoteDTO getConnote() {
        return restTemplate.getForObject("http://connote-service/connote/create", ConnoteDTO.class);
    }
}
