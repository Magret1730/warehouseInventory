package org.codewithmagret.rest.product;

import org.codewithmagret.rest.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order entity operations.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Finds all products
     * @return list of all products
     */
    List<Product> findAll();

    /**
     * Finds a product
     * @param id id of product
     * @return product with the given id or empty if not found
     */
    Optional<Product> findById(Long id);

}
