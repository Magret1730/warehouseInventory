package org.codewithmagret.rest.order;

import org.codewithmagret.rest.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Order entity operations.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    /**
     * Finds an order by id
     * @param id id of order
     * @return order with the given id, or empty if not found
     */
    Optional<Order> findById(Long id);

    /**
     * Finds customer orders by customer email
     * @param email email of customer
     * @return list of orders for the customer
     */
    List<Order> findByCustomerEmail(String email);
}
