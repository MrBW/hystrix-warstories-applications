package com.codecentric.hystrix.warstories.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Matchers.anyLong;
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
import com.codecentric.hystrix.warstories.service.CustomerService;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

        // Add Mock to RestController
        mockMvc = MockMvcBuilders.standaloneSetup(new CustomerController(customerService)).build();

    }

    @BeforeClass
    public static void beforeClass() {
        LogManager.getRootLogger().setLevel(Level.DEBUG);
    }

    @Test
    public void findCustomerByName_Goodcase() throws Exception {

        CustomerDTO customer = createCustomer();

        when(customerService.findCustomerByAccountNumber(anyLong())).thenReturn(customer);

        //@formatter:off
        mockMvc.perform(MockMvcRequestBuilders.get("/customer/find/accountnumber/4711")).andDo(print())
                .andExpect(status().isOk())
               .andExpect(jsonPath("$.accountNumber",is(notNullValue())))
               .andExpect(jsonPath("$.name",is(customer.getName())))
               .andExpect(jsonPath("$.firstName",is(customer.getFirstName())));
        //@formatter:on

    }

    private CustomerDTO createCustomer() {
        CustomerDTO customer = new CustomerDTO(100l, "firstname", "lastname");

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