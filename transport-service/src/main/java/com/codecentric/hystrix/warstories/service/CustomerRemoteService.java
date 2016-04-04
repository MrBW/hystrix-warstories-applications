package com.codecentric.hystrix.warstories.service;

import java.net.URI;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author Benjamin Wilms (xd98870)
 */

@Service
public class CustomerRemoteService {

    private static final Log LOGGER = LogFactory.getLog(CustomerRemoteService.class);

    private DynamicStringProperty customerServiceAddress = DynamicPropertyFactory.getInstance()
        .getStringProperty("service.address.customer", "http://customer-service:8080/customer/find/accountnumber/");

    @HystrixCommand(commandKey = "CustomerClientCmdKey", threadPoolKey = "CustomerClientThreadPool")
    public CustomerDTO findCustomer(long accountnumber) {
        Assert.notNull(accountnumber);
        Assert.hasText(customerServiceAddress.get());

        LOGGER.debug("Calling Connote Remote Service: " + customerServiceAddress.get());

        RestTemplate client = new RestTemplate();
        URI uri = URI.create(customerServiceAddress.get() + accountnumber);
        ResponseEntity<CustomerDTO> dtoResponseEntity = client.getForEntity(uri, CustomerDTO.class);

        return dtoResponseEntity.getBody();
    }
}
