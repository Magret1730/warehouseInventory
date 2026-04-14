package org.codewithmagret.rest.product;

import org.codewithmagret.rest.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order entity operations.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
}
