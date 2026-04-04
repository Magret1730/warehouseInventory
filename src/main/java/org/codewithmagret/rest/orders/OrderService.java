package org.codewithmagret.rest.orders;

import org.codewithmagret.rest.customer.Customer;
import org.codewithmagret.rest.customer.CustomerRepository;
import org.codewithmagret.rest.orders.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Service class responsible for handling order-related business logic.
 * This includes creating, retrieving, updating, and deleting orders.
 */
@Service
public class OrderService {

    /**
     * Repository used for order database operations.
     */
    private final OrderRepository orderRepository;

    /**
     * Repository used for customer database operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Creates a new OrderService with the required repositories.
     *
     * @param orderRepository the order repository
     * @param customerRepository the customer repository
     */
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Creates and saves a new order after validating the request data.
     *
     * @param requestDTO the order request data
     * @return the saved order
     * @throws IllegalArgumentException if the request data is invalid
     */
    public Order createOrder(OrderRequestDTO requestDTO) {
        validateOrderRequest(requestDTO);

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with id: " + requestDTO.getCustomerId()
                ));

        Order order = new Order();
        order.setOrderDate(requestDTO.getOrderDate());
        order.setPriorityLevel(requestDTO.getPriorityLevel());
        order.setCustomer(customer);

        return orderRepository.save(order);
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves a single order by its ID.
     *
     * @param id the ID of the order
     * @return the matching order
     * @throws IllegalArgumentException if no order is found with the given ID
     */
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + id));
    }

    /**
     * Updates an existing order using the provided request data.
     *
     * @param id the ID of the order to update
     * @param requestDTO the updated order request data
     * @return the updated order
     * @throws IllegalArgumentException if the order does not exist or the request is invalid
     */
    public Order updateOrder(Long id, OrderRequestDTO requestDTO) {
        validateOrderRequest(requestDTO);

        Order existingOrder = getOrderById(id);

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with id: " + requestDTO.getCustomerId()
                ));

        existingOrder.setOrderDate(requestDTO.getOrderDate());
        existingOrder.setPriorityLevel(requestDTO.getPriorityLevel());
        existingOrder.setCustomer(customer);

        return orderRepository.save(existingOrder);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @throws IllegalArgumentException if the order does not exist
     */
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    /**
     * Validates order request data before save or update operations.
     *
     * @param requestDTO the order request data to validate
     * @throws IllegalArgumentException if the request is null or contains invalid values
     */
    private void validateOrderRequest(OrderRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Order request cannot be null");
        }

        if (requestDTO.getOrderDate() == null) {
            throw new IllegalArgumentException("Order date is required");
        }

        if (requestDTO.getPriorityLevel() < 1 || requestDTO.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }

        if (requestDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer id is required");
        }
    }
}