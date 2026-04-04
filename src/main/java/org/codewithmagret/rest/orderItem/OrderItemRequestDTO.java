package org.codewithmagret.rest.orderItem;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Represents the request body for creating or updating an order item.
 * This class contains the quantity ordered, the product ID,
 * and the order ID the item belongs to.
 */
public class OrderItemRequestDTO {

    /**
     * The quantity of the product ordered.
     * Must be at least 1.
     */
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    /**
     * The ID of the product associated with the order item.
     * Must not be null.
     */
    @NotNull(message = "Product id is required")
    private Long productId;

    /**
     * The ID of the order associated with the order item.
     * Must not be null.
     */
    @NotNull(message = "Order id is required")
    private Long orderId;

    /**
     * Default constructor.
     */
    public OrderItemRequestDTO() {
    }

    /**
     * Gets the quantity of the product ordered.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product ordered.
     *
     * @param quantity the quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the product ID.
     *
     * @return the product ID
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the product ID.
     *
     * @param productId the product ID
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * Gets the order ID.
     *
     * @return the order ID
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * Sets the order ID.
     *
     * @param orderId the order ID
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}