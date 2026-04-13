package org.codewithmagret.rest.orders.bst;

import org.codewithmagret.rest.orders.Order;

/**
 * Represents a node in the Order Binary Search Tree.
 * Each node stores an Order and references to left and right child nodes.
 */
public class OrderNode {

    /**
     * The order stored in the node.
     */
    Order order;

    /**
     * Reference to the left child node.
     */
    OrderNode left;

    /**
     * Reference to the right child node.
     */
    OrderNode right;
}