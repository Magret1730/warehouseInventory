package org.codewithmagret.rest.orderItem;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repository for OrderItem entity operations.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    /**
     * Find order items by order id
     * @param orderId order id
     * @return list of order items for the order
     */
    List<OrderItem> findByOrderId(Long orderId);

    /**
     * Find order items by product id
     * @param productId product id
     * @return list of order items for the order
     */
    List<OrderItem> findByProductId(Long productId);

    /**
     * Find order items by order id and product id
     * @param orderId order id
     * @param productId product id
     * @return list of order items for the order and product
     */
    List<OrderItem> findByOrderIdAndProductId(Long orderId, Long productId);
}
