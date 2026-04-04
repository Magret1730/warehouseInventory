package org.codewithmagret.rest.orders;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
     * Must not be null and cannot be in the future.
     */
    @NotNull(message = "Order date is required")
    @PastOrPresent(message = "Order date cannot be in the future")
    private LocalDate orderDate;

    /**
     * The priority level of the order.
     * Must be between 1 and 10.
     */
    @NotNull(message = "Priority level is required")
    @Min(value = 1, message = "Priority level must be at least 1")
    @Max(value = 10, message = "Priority level must not be greater than 10")
    private Integer priorityLevel;

    /**
     * The ID of the customer placing the order.
     * Must not be null.
     */
    @NotNull(message = "Customer id is required")
    private Long customerId;

    /**
     * The list of items included in the order.
     * Must not be null or empty.
     * Each item in the list will also be validated.
     */
    @NotEmpty(message = "Order must contain at least one item")
    @Valid
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
    public Integer getPriorityLevel() {
        return priorityLevel;
    }

    /**
     * Sets the priority level of the order.
     *
     * @param priorityLevel the priority level
     */
    public void setPriorityLevel(Integer priorityLevel) {
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