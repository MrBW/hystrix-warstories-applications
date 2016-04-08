package com.codecentric.hystrix.warstories.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;
import com.codecentric.hystrix.warstories.shared.utils.ChaosMonkey;
import org.modelmapper.ModelMapper;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private ChaosMonkey chaosMonkeyMock;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @Mock
    private ModelMapper modelMapperMock;

    @Before
    public void setUp() throws Exception {

        // Add Mock to RestController
        customerService = new CustomerService(customerRepositoryMock, chaosMonkeyMock, modelMapperMock);
    }

    @BeforeClass
    public static void beforeClass() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void findCustomerByAccountNumber_GoodCase() throws Exception {

        Customer customer = createCustomer();

        when(customerRepositoryMock.findByAccountNumber(anyLong())).thenReturn(customer);

        CustomerDTO customerByAccountNumber = customerService.findCustomerByAccountNumber(4711);

        assertNotNull(customerByAccountNumber);
        assertThat(customerByAccountNumber.getAccountNumber(), is(customer.getAccountNumber()));
        assertThat(customerByAccountNumber.getFirstName(), is(customer.getFirstName()));
        assertThat(customerByAccountNumber.getName(), is(customer.getLastName()));

    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setLastName("last");
        customer.setFirstName("first");
        customer.setAccountNumber(4711l);

        return customer;
    }
}