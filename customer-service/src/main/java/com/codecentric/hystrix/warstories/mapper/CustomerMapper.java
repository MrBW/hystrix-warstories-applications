package com.codecentric.hystrix.warstories.mapper;

import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;
import org.modelmapper.PropertyMap;
import com.codecentric.hystrix.warstories.entities.Customer;

/**
 * Customer Mapper
 * @author Benjamin Wilms (xd98870)
 */
public class CustomerMapper extends PropertyMap<Customer, CustomerDTO> {

    protected void configure() {
        map().setFirstName(source.getFirstName());
        map().setName(source.getLastName());
        map().setAccountNumber(source.getAccountNumber());

    }
};