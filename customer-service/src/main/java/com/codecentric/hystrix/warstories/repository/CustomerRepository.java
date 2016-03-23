package com.codecentric.hystrix.warstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecentric.hystrix.warstories.entities.Customer;

/**
 * @author Benjamin Wilms (xd98870)
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /***
     * Returns Customer object searched by account number
     * @param accountNumber
     * @return Customer with account number
     */
    Customer findByAccountNumber(long accountNumber);

}
