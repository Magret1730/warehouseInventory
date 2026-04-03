package org.codewithmagret.rest.order;

import org.codewithmagret.rest.orderItem.OrderItemRequestDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents the request body for creating a new order.
 * This class contains the customer placing the order,
 * the order details, and the list of items included in the order.
 */
public class OrderRequestDTO {

    /**
     * The date the order was placed.
     */
    private LocalDate orderDate;

    /**
     * The priority level of the order.
     */
    private int priorityLevel;

    /**
     * The ID of the customer placing the order.
     */
    private Long customerId;

    /**
     * The list of items included in the order.
     */
    private List<OrderItemRequestDTO> items;

    /**
     * Default constructor.
     */
    public OrderRequestDTO() {
    }

    /**
     * Gets the order date.
     *
     * @return the order date
     */
    public LocalDate getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the order date.
     *
     * @param orderDate the order date
     */
    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the priority level of the order.
     *
     * @return the priority level
     */
    public int getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Sets the priority level of the order.
     *
     * @param priorityLevel the priority level
     */
    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * Gets the customer ID.
     *
     * @return the customer ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the customer ID.
     *
     * @param customerId the customer ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * Gets the list of order items.
     *
     * @return the list of order items
     */
    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    /**
     * Sets the list of order items.
     *
     * @param items the list of order items
     */
    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}
