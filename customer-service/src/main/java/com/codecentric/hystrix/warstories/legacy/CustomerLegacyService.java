package com.codecentric.hystrix.warstories.legacy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;

/**
 * Simulates legacy backend with horrorable response times and exceptions
 * @author Benjamin Wilms (xd98870)
 */
@Service
public class CustomerLegacyService {

    private static final Log LOGGER = LogFactory.getLog(CustomerLegacyService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerLegacyService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;

    }

    public Customer findByLastName(String lastName) {
        return null;
    }

    public Customer findByFirstName(String firstName) {
        return null;
    }

    public Customer findByAccountNumber(long accountNumber) {

        LOGGER.debug("findByAccountNumber via LEGACY");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        Customer customer = customerRepository.findByAccountNumber(accountNumber);

        return customer;
    }
}
