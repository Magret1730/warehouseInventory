package org.codewithmagret.rest.orderItem;

/**
 * Represents the request body for creating a new order item.
 * This class is used as part of an order creation request.
 */
public class OrderItemRequestDTO {

    /**
     * The ID of the product being ordered.
     */
    private Long productId;

    /**
     * The quantity of the product being ordered.
     */
    private int quantity;

    /**
     * Default constructor.
     */
    public OrderItemRequestDTO() {
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
     * Gets the quantity of the product.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
