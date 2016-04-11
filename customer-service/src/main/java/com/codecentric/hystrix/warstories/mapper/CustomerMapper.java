package com.codecentric.hystrix.warstories.mapper;

import org.modelmapper.PropertyMap;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;

/**
 * Customer Mapper
 * @author Benjamin Wilms (xd98870)
 */
public class CustomerMapper extends PropertyMap<Customer, CustomerDTO> {

    protected void configure() {
        map().setFirstName(source.getFirstName());
        map().setName(source.getLastName());
        map().setAccountNumber(source.getAccountNumber());
        map().setFallback(false);
        map().setErrorMsg(null);

    }
}