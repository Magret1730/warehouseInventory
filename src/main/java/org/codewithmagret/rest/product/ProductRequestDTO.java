package org.codewithmagret.rest.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * Represents the request body for creating a new product.
 * This class is used to receive product data from the client.
 */
public class ProductRequestDTO {
    /**
     * The name of the product.
     * Must not be null, empty, or blank.
     */
    @NotBlank(message = "Product name is required.")
    private String name;

    /**
     * The description of the product.
     * Must not be null, empty or blank.
     */
    @NotBlank(message = "Product description is required.")
    private String description;

    /**
     * The price of the product.
     * Must be zero or greater.
     */
    @Min(value = 0, message = "Product price cannot be negative")
    private double price;

    /**
     * Default constructor.
     */
    public ProductRequestDTO() {
    }

    /**
     * Gets the product name.
     *
     * @return the product name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the product name.
     *
     * @param name the product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the product description.
     *
     * @return the product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the product description.
     *
     * @param description the product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the product price.
     *
     * @param price the product price
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
