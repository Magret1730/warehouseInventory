package org.codewithmagret.rest.product.dto;

/**
 * Represents product data returned to the client.
 */
public class ProductResponseDTO {

    /**
     * The product ID.
     */
    private Long id;

    /**
     * The product name.
     */
    private String name;

    /**
     * The available stock quantity of the product.
     */
    private int stock;

    /**
     * The product price.
     */
    private double price;

    /**
     * Default constructor.
     */
    public ProductResponseDTO() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id the product ID
     * @param name the product name
     * @param stock the available stock quantity
     * @param price the product price
     */
    public ProductResponseDTO(Long id, String name, int stock, double price) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the available stock quantity.
     *
     * @return the stock quantity
     */
    public int getStock() {
        return stock;
    }

    /**
     * Sets the available stock quantity.
     *
     * @param stock the stock quantity
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
