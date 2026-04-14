package org.codewithmagret.rest.orders;

import org.codewithmagret.rest.orders.dto.OrderRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling order-related API requests.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    /**
     * Service for order-related business logic.
     */
    private final OrderService orderService;

    /**
     * Creates an OrderController with the given order service.
     *
     * @param orderService the order service
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of all orders
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    /**
     * Retrieves an order by ID.
     *
     * @param id the order ID
     * @return the order
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    /**
     * Creates a new order.
     *
     * @param request the order creation request
     * @return the created order
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequestDTO request) {
        Order createdOrder = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    /**
     * Updates an existing order.
     * @param id the ID of the order to update
     * @param request the updated order data
     * @return the updated order
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody OrderRequestDTO request) {
        Order updatedOrder = orderService.updateOrder(id, request);
        return ResponseEntity.ok(updatedOrder);
    }

    /**
     * Deletes an order by ID.
     * @param id the ID of the order to delete
     * @return a response entity with no content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Adds all current orders into the priority BST.
     *
     * @return a success message
     */
    @PostMapping("/add-to-priority-tree")
    public ResponseEntity<String> addOrdersToPriorityTree() {
        orderService.addOrdersToPriorityTree();
        return ResponseEntity.ok("Orders added to priority tree successfully.");
    }
}