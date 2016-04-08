package com.codecentric.hystrix.warstories.service;

import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Benjamin Wilms (xd98870)
 */
@Service
public class ConnoteRemoteService {

    private static final Log LOGGER = LogFactory.getLog(ConnoteRemoteService.class);

    private DynamicStringProperty connoteServiceAddress = DynamicPropertyFactory.getInstance()
        .getStringProperty("service.address.connote", "http://connote-service:8080/connote/create");

    @HystrixCommand(commandKey = "ConnoteClientCmdKey", threadPoolKey = "ConnoteClientThreadPool", fallbackMethod = "fallback")
    public ConnoteDTO createConnote() {
        Assert.hasText(connoteServiceAddress.get());

        LOGGER.debug("Calling Connote Remote Service: " + connoteServiceAddress.get());

        RestTemplate client = new RestTemplate();
        URI uri = URI.create(connoteServiceAddress.get());
        ResponseEntity<ConnoteDTO> dtoResponseEntity = client.getForEntity(uri, ConnoteDTO.class);

        if (dtoResponseEntity.getStatusCode().is2xxSuccessful())
            return dtoResponseEntity.getBody();
        else {
            ConnoteDTO fallbackDTO = new ConnoteDTO();
            fallbackDTO.setFallback(false);
            fallbackDTO.setErrorMsg("HTTP Status Code: " + dtoResponseEntity.getStatusCode());
            return fallbackDTO;
        }
    }

    /***
     * @return HTTP Status 503
     */
    public ConnoteDTO fallback(Throwable throwable) {

        LOGGER.error("Hystrix Fallback, cause: " + throwable);

        ConnoteDTO fallbackDTO = new ConnoteDTO();
        fallbackDTO.setFallback(true);
        fallbackDTO.setErrorMsg("Transport > Connote: " + throwable.getMessage());

        return fallbackDTO;
    }
}
