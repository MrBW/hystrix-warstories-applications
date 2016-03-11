package com.codecentric.hystrix.warstories.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.codecentric.hystrix.warstories.entities.Customer;
import com.codecentric.hystrix.warstories.repository.CustomerRepository;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepositoryMock;

    @Before
    public void setUp() throws Exception {

        // Add Mock to RestController
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerService(customerRepositoryMock)).build();

    }

    @BeforeClass
    public static void beforeClass() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void findCustomerByName_Goodcase() throws Exception {

        Customer customer = createCustomer();

        when(customerRepositoryMock.findByLastName(anyString())).thenReturn(customer);

        //@formatter:off
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/find/name/Meier")).andDo(print())
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.accountNumber",is(notNullValue())))
               .andExpect(jsonPath("$.name",is(customer.getLastName())))
               .andExpect(jsonPath("$.firstName",is(customer.getFirstName())));
        //@formatter:on

    }

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setAccountNumber(100l);
        customer.setLastName("last");
        customer.setFirstName("first");

        return customer;
    }

    @Test
    public void findCustomerByFirstName() throws Exception {

    }

    @Test
    public void findCustomerByAccountNumber() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }
}