package com.codecentric.hystrix.warstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecentric.hystrix.warstories.entities.Customer;

/**
 * @author Benjamin Wilms (xd98870)
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLastName(String lastName);

    Customer findByFirstName(String firstName);

    Customer findByAccountNumber(long accountNumber);

}
