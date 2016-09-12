package com.codecentric.hystrix.warstories.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.mapper.CustomerMapper;

/**
 * @author Benjamin Wilms (xd98870)
 */

public class CustomerMapperTest {

    private CustomerMapper customerMapper;

    private ModelMapper modelMapper;

    @Before
    public void setUp() throws Exception {
        customerMapper = new CustomerMapper();
        modelMapper = new ModelMapper();
        modelMapper.addMappings(customerMapper);

    }

    @Test
    public void map_Customer_CustomerDTO_Goodcase() throws Exception {

        long accountNumber = 100;
        String firstName = "first";
        String lastName = "last";
        Customer customer = new Customer();
        customer.setLastName(lastName);
        customer.setFirstName(firstName);
        customer.setAccountNumber(accountNumber);

        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);

        assertNotNull(customerDTO);
        assertThat(customerDTO.getAccountNumber(), is(accountNumber));
        assertThat(customerDTO.getFirstName(), is(firstName));
        assertThat(customerDTO.getName(), is(lastName));

    }
}