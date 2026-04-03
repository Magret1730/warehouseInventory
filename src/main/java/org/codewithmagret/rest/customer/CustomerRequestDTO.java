package org.codewithmagret.rest.customer;

/**
 * Represents the request body for creating a new customer.
 * This class is used to receive customer data from the client.
 */
public class CustomerRequestDTO {

    /**
     * The name of the customer.
     */
    private String name;

    /**
     * The email address of the customer.
     */
    private String email;

    /**
     * Default constructor.
     */
    public CustomerRequestDTO() {
    }

    /**
     * Gets the customer name.
     *
     * @return the customer name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the customer name.
     *
     * @param name the customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the customer email.
     *
     * @return the customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the customer email.
     *
     * @param email the customer email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
