package com.codecentric.hystrix.warstories.legacy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;
import com.codecentric.hystrix.warstories.shared.utils.ChaosMonkey;

/**
 * Simulates legacy backend with horrorable response times and exceptions
 * @author Benjamin Wilms (xd98870)
 */
@Service
public class CustomerLegacyService {

    private static final Log LOGGER = LogFactory.getLog(CustomerLegacyService.class);

    private CustomerRepository customerRepository;

    private ChaosMonkey chaosMonkey;

    @Autowired
    public CustomerLegacyService(CustomerRepository customerRepository, ChaosMonkey chaosMonkey) {
        this.customerRepository = customerRepository;
        this.chaosMonkey = chaosMonkey;
    }

    public Customer findByLastName(String lastName) {
        return null;
    }

    public Customer findByFirstName(String firstName) {
        return null;
    }

    public Customer findByAccountNumber(long accountNumber) {

        LOGGER.debug("findByAccountNumber via LEGACY");

        Customer customer = customerRepository.findByAccountNumber(accountNumber);

        // Call the chaos monkey
        chaosMonkey.iWantTrouble();

        return customer;
    }
}
