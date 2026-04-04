package org.codewithmagret.rest.customer;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling customer-related business logic.
 * This includes creating, retrieving, updating, and deleting customers.
 */
@Service
public class CustomerService {

    /**
     * Repository used for customer database operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Creates a new CustomerService with the required repository.
     *
     * @param customerRepository the customer repository
     */
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Creates and saves a new customer after validating the request data.
     *
     * @param requestDTO the customer request data
     * @return the saved customer
     * @throws IllegalArgumentException if the request data is invalid
     */
    public Customer createCustomer(CustomerRequestDTO requestDTO) {
        validateCustomerRequest(requestDTO);

        Customer customer = new Customer();
        customer.setName(requestDTO.getName().trim());
        customer.setEmail(requestDTO.getEmail().trim());

        return customerRepository.save(customer);
    }

    /**
     * Retrieves all customers from the database.
     *
     * @return a list of all customers
     */
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer by its ID.
     *
     * @param id the ID of the customer
     * @return the matching customer
     * @throws IllegalArgumentException if no customer is found with the given ID
     */
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    /**
     * Updates an existing customer using the provided request data.
     *
     * @param id the ID of the customer to update
     * @param requestDTO the updated customer request data
     * @return the updated customer
     * @throws IllegalArgumentException if the customer does not exist or the request is invalid
     */
    public Customer updateCustomer(Long id, CustomerRequestDTO requestDTO) {
        validateCustomerRequest(requestDTO);

        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(requestDTO.getName().trim());
        existingCustomer.setEmail(requestDTO.getEmail().trim());

        return customerRepository.save(existingCustomer);
    }

    /**
     * Deletes a customer by its ID.
     *
     * @param id the ID of the customer to delete
     * @throws IllegalArgumentException if the customer does not exist
     */
    public void deleteCustomer(Long id) {
        Customer customer = getCustomerById(id);
        customerRepository.delete(customer);
    }

    /**
     * Validates customer request data before save or update operations.
     *
     * @param requestDTO the customer request data to validate
     * @throws IllegalArgumentException if the request is null or contains invalid values
     */
    private void validateCustomerRequest(CustomerRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Customer request cannot be null");
        }

        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }

        if (requestDTO.getEmail() == null || requestDTO.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email is required");
        }
    }
}