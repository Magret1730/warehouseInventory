package org.codewithmagret.rest.orderItem.dto;

/**
 * Represents order item data returned to the client.
 */
public class OrderItemResponseDTO {

    /**
     * The order item ID.
     */
    private Long id;

    /**
     * The product ID.
     */
    private Long productId;

    /**
     * The product name.
     */
    private String productName;

    /**
     * The quantity ordered.
     */
    private int quantity;

    /**
     * The product price.
     */
    private double price;

    /**
     * Default constructor.
     */
    public OrderItemResponseDTO() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id the item ID
     * @param productId the product ID
     * @param productName the product name
     * @param quantity the quantity
     * @param price the price
     */
    public OrderItemResponseDTO(Long id, Long productId, String productName, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}