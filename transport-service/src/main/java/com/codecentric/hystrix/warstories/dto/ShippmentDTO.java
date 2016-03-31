package com.codecentric.hystrix.warstories.dto;

import com.codecentric.hystrix.warstories.shared.dto.ConnoteDTO;
import com.codecentric.hystrix.warstories.shared.dto.CustomerDTO;

/**
 * @author Benjamin Wilms (xd98870)
 */
public class ShippmentDTO {

    private CustomerDTO customerDTO;

    private ConnoteDTO connoteDTO;

    public ShippmentDTO() { // Default
    }

    public ShippmentDTO(ConnoteDTO connote, CustomerDTO customer) {
        this.connoteDTO = connote;
        this.customerDTO = customer;
    }

    public ConnoteDTO getConnoteDTO() {
        return connoteDTO;
    }

    public void setConnoteDTO(ConnoteDTO connoteDTO) {
        this.connoteDTO = connoteDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
