package com.codecentric.hystrix.warstories.rest;

import java.util.LinkedList;
import java.util.List;

import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.legacy.CustomerLegacyService;
import com.codecentric.hystrix.warstories.mapper.CustomerMapper;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;
import com.netflix.config.DynamicBooleanProperty;
import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RestController
@RequestMapping("customer/")
public class CustomerController {

    private static final Log LOGGER = LogFactory.getLog(CustomerController.class);

    private ModelMapper modelMapper;

    private CustomerRepository customerRepository;

    private CustomerLegacyService customerLegacyService;

    // use simulated legacy system?
    private DynamicBooleanProperty useLegacySystem =
        DynamicPropertyFactory.getInstance().getBooleanProperty("legacy.systems.active", false);

    private DynamicStringProperty testValueChange = DynamicPropertyFactory.getInstance().getStringProperty("test", "default");

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerLegacyService customerLegacyService) {
        this.customerRepository = customerRepository;
        this.customerLegacyService = customerLegacyService;
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
        LOGGER.debug("VALUE CHANGE: " + useLegacySystem.get());

        Customer customer;
        if (useLegacySystem.get())
            customer = customerLegacyService.findByAccountNumber(number);
        else
            customer = customerRepository.findByAccountNumber(number);

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
