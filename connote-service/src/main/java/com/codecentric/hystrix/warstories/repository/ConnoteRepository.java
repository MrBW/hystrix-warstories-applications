package com.codecentric.hystrix.warstories.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codecentric.hystrix.warstories.entities.Connote;

/**
 * @author Benjamin Wilms (xd98870)
 */
public interface ConnoteRepository extends JpaRepository<Connote, Long> {

    Connote save(Connote connote);
}
