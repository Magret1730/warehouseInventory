package org.codewithmagret.rest.orders.dto;

import org.codewithmagret.rest.orderItem.dto.OrderItemResponseDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents order data returned to the client.
 */
public class OrderResponseDTO {

    /**
     * The order ID.
     */
    private Long id;

    /**
     * The order date.
     */
    private LocalDate orderDate;

    /**
     * The priority level.
     */
    private int priorityLevel;

    /**
     * The customer ID.
     */
    private Long customerId;

    /**
     * The customer name.
     */
    private String customerName;

    /**
     * The items in the order.
     */
    private List<OrderItemResponseDTO> items;

    /**
     * Default constructor.
     */
    public OrderResponseDTO() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id the order ID
     * @param orderDate the order date
     * @param priorityLevel the priority level
     * @param customerId the customer ID
     * @param customerName the customer name
     * @param items the order items
     */
    public OrderResponseDTO(Long id, LocalDate orderDate, int priorityLevel, Long customerId, String customerName, List<OrderItemResponseDTO> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.priorityLevel = priorityLevel;
        this.customerId = customerId;
        this.customerName = customerName;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<OrderItemResponseDTO> getItems() {
        return items;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setItems(List<OrderItemResponseDTO> items) {
        this.items = items;
    }
}