package com.codecentric.hystrix.warstories.rest;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codecentric.hystrix.warstories.dto.CustomerDTO;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.mapper.CustomerMapper;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
@RequestMapping("customer/")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new CustomerMapper());
    }

    @RequestMapping("find/name/{name}")
    public CustomerDTO findCustomerByName(@PathVariable String name) {
        CustomerDTO customerDTO = null;
        Customer customer = customerRepository.findByLastName(name);

        if (customer != null)
            customerDTO = modelMapper.map(customer, CustomerDTO.class);

        return customerDTO;

    }

    @RequestMapping("find/firstname/{firstname}")
    public CustomerDTO findCustomerByFirstName(@PathVariable String firstname) {
        CustomerDTO customerDTO = null;
        Customer customer = customerRepository.findByFirstName(firstname);

        if (customer != null)
            customerDTO = modelMapper.map(customer, CustomerDTO.class);

        return customerDTO;

    }

    @RequestMapping("find/accountnumber/{number}")
    public CustomerDTO findCustomerByAccountNumber(@PathVariable long number) {
        CustomerDTO customerDTO = null;
        Customer customer = customerRepository.findByAccountNumber(number);

        if (customer != null)
            customerDTO = modelMapper.map(customer, CustomerDTO.class);

        return customerDTO;

    }

    @RequestMapping("find/all")
    public List<CustomerDTO> findAll() {
        List<CustomerDTO> customerDTOList = new LinkedList<>();

        List<Customer> customerList = customerRepository.findAll();

        if (customerList != null) {
            for (Customer customer : customerList) {
                customerDTOList.add(modelMapper.map(customer, CustomerDTO.class));
            }
        }

        return customerDTOList;


    }
}
