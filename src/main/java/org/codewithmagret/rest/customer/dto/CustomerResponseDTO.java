package org.codewithmagret.rest.customer.dto;

/**
 * Represents customer data returned to the client.
 */
public class CustomerResponseDTO {

    /**
     * The customer ID.
     */
    private Long id;

    /**
     * The customer name.
     */
    private String name;

    /**
     * The customer email.
     */
    private String email;

    /**
     * Default constructor.
     */
    public CustomerResponseDTO() {
    }

    /**
     * Parameterized constructor.
     *
     * @param id the customer ID
     * @param name the customer name
     * @param email the customer email
     */
    public CustomerResponseDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Gets the customer ID.
     *
     * @return the customer ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the customer ID.
     *
     * @param id the customer ID
     */
    public void setId(Long id) {
        this.id = id;
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