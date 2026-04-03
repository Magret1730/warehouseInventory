package org.codewithmagret.rest.customer;

import org.springframework.data.jpa.repository.JpaRepository;

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
     * Finds a customer by email.
     * @param email email of customer
     * @return customer with the given email, or empty if not found
     */
    Optional<Customer> findByEmail(String email);
}
