package com.codecentric.hystrix.warstories.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codecentric.hystrix.warstories.service.CustomerService;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
@RequestMapping("customer/")
public class CustomerController {

    private static final Log LOGGER = LogFactory.getLog(CustomerController.class);

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {

        this.customerService = customerService;
    }

    @RequestMapping("find/accountnumber/{number}")
    public CustomerDTO findCustomerByAccountNumber(@PathVariable long number) {
        LOGGER.debug(LOGGER.isDebugEnabled() ? "Search Customer by Account Number: " + number : null);

        return customerService.findCustomerByAccountNumber(number);
    }

}
