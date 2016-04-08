package com.codecentric.hystrix.warstories.shared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Benjamin Wilms (xd98870)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO extends FallbackDTO {

    private Long accountNumber;

    private String name;

    private String firstName;

    public CustomerDTO(Long accountNumber, String firstName, String name) {
        this.accountNumber = accountNumber;
        this.firstName = firstName;
        this.name = name;
    }

    // JSON Default
    public CustomerDTO() {
        super();
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
