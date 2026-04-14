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

    /**
     *  Gets the unique identifier of the product.
     * @return the product ID
     */
    public Long getId() {
        return id;
    }

    /* Sets the unique identifier of the product.
     * @param id the product ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the product.
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name the product name
     */
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

    /**
     * Gets the price of the product.
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
