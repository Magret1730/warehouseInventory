package org.codewithmagret.rest.customer;

import org.codewithmagret.rest.customer.dto.CustomerRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling customer-related API requests.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    /**
     * Service for customer-related business logic.
     */
    private final CustomerService customerService;

    /**
     * Creates a CustomerController with the given customer service.
     *
     * @param customerService the customer service
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of all customers
     */
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    /**
     * Retrieves a customer by ID.
     *
     * @param id the customer ID
     * @return the customer
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    /**
     * Creates a new customer.
     *
     * @param request the customer creation request
     * @return the created customer
     */
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody CustomerRequestDTO request) {
        Customer createdCustomer = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }
}
