package org.codewithmagret.rest.orders;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling BST priority-related order requests.
 */
@RestController
@RequestMapping("/orders/priority")
public class OrderPriorityController {

    /**
     * Service for order-related business logic.
     */
    private final OrderService orderService;

    /**
     * Creates an OrderPriorityController with the given order service.
     *
     * @param orderService the order service
     */
    public OrderPriorityController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Returns orders from the priority BST using inorder traversal.
     *
     * @return a sorted list of orders by priority
     */
    @GetMapping("/inorder")
    public ResponseEntity<List<Order>> getOrdersInPriorityOrder() {
        return ResponseEntity.ok(orderService.getOrdersInPriorityOrder());
    }

    /**
     * Returns the highest-priority order from the BST.
     *
     * @return the highest-priority order
     */
    @GetMapping("/highest")
    public ResponseEntity<Order> getHighestPriorityOrder() {
        return ResponseEntity.ok(orderService.getHighestPriorityOrder());
    }

    /**
     * Returns the lowest-priority order from the BST.
     *
     * @return the lowest-priority order
     */
    @GetMapping("/lowest")
    public ResponseEntity<Order> getLowestPriorityOrder() {
        return ResponseEntity.ok(orderService.getLowestPriorityOrder());
    }
}
