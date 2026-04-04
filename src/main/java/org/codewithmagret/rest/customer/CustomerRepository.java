package org.codewithmagret.rest.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Customer entity operations.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    /**
     * Checks if a customer exists with the given email.
     *
     * @param email customer email
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Checks if a customer exists with the given id.
     *
     * @param id customer id
     * @return true if exists, false otherwise
     */
    boolean existsById(String id);

    /**
     * Finds a customer by email.
     * @param email email of customer
     * @return customer with the given email, or empty if not found
     */
    List<Customer> findByEmail(String email);
}
