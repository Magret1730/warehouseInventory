package org.codewithmagret.rest.orderItem;

import org.codewithmagret.rest.orders.Order;
import org.codewithmagret.rest.orders.OrderRepository;
import org.codewithmagret.rest.product.Product;
import org.codewithmagret.rest.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling order item-related business logic.
 * This includes creating, retrieving, updating, and deleting order items.
 */
@Service
public class OrderItemService {

    /**
     * Repository used for order item database operations.
     */
    private final OrderItemRepository orderItemRepository;

    /**
     * Repository used for product database operations.
     */
    private final ProductRepository productRepository;

    /**
     * Repository used for order database operations.
     */
    private final OrderRepository orderRepository;

    /**
     * Creates a new OrderItemService with the required repositories.
     *
     * @param orderItemRepository the order item repository
     * @param productRepository the product repository
     * @param orderRepository the order repository
     */
    public OrderItemService(OrderItemRepository orderItemRepository,
                            ProductRepository productRepository,
                            OrderRepository orderRepository) {
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Creates and saves a new order item after validating the request data.
     *
     * @param requestDTO the order item request data
     * @return the saved order item
     * @throws IllegalArgumentException if the request data is invalid
     */
    public OrderItem createOrderItem(OrderItemRequestDTO requestDTO) {
        validateOrderItemRequest(requestDTO);

        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found with id: " + requestDTO.getProductId()
                ));

        Order order = orderRepository.findById(requestDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Order not found with id: " + requestDTO.getOrderId()
                ));

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(requestDTO.getQuantity());
        orderItem.setProduct(product);
        orderItem.setOrder(order);

        return orderItemRepository.save(orderItem);
    }

    /**
     * Retrieves all order items from the database.
     *
     * @return a list of all order items
     */
    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    /**
     * Retrieves a single order item by its ID.
     *
     * @param id the ID of the order item
     * @return the matching order item
     * @throws IllegalArgumentException if no order item is found with the given ID
     */
    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order item not found with id: " + id));
    }

    /**
     * Updates an existing order item using the provided request data.
     *
     * @param id the ID of the order item to update
     * @param requestDTO the updated order item request data
     * @return the updated order item
     * @throws IllegalArgumentException if the order item does not exist or the request is invalid
     */
    public OrderItem updateOrderItem(Long id, OrderItemRequestDTO requestDTO) {
        validateOrderItemRequest(requestDTO);

        OrderItem existingOrderItem = getOrderItemById(id);

        Product product = productRepository.findById(requestDTO.getProductId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found with id: " + requestDTO.getProductId()
                ));

        Order order = orderRepository.findById(requestDTO.getOrderId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Order not found with id: " + requestDTO.getOrderId()
                ));

        existingOrderItem.setQuantity(requestDTO.getQuantity());
        existingOrderItem.setProduct(product);
        existingOrderItem.setOrder(order);

        return orderItemRepository.save(existingOrderItem);
    }

    /**
     * Deletes an order item by its ID.
     *
     * @param id the ID of the order item to delete
     * @throws IllegalArgumentException if the order item does not exist
     */
    public void deleteOrderItem(Long id) {
        OrderItem orderItem = getOrderItemById(id);
        orderItemRepository.delete(orderItem);
    }

    /**
     * Validates order item request data before save or update operations.
     *
     * @param requestDTO the order item request data to validate
     * @throws IllegalArgumentException if the request is null or contains invalid values
     */
    private void validateOrderItemRequest(OrderItemRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Order item request cannot be null");
        }

        if (requestDTO.getQuantity() == null) {
            throw new IllegalArgumentException("Quantity is required");
        }

        if (requestDTO.getQuantity() < 1) {
            throw new IllegalArgumentException("Quantity must be at least 1");
        }

        if (requestDTO.getProductId() == null) {
            throw new IllegalArgumentException("Product id is required");
        }

        if (requestDTO.getOrderId() == null) {
            throw new IllegalArgumentException("Order id is required");
        }
    }
}