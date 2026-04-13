package org.codewithmagret.rest.orders.bst;

import org.codewithmagret.rest.orders.Order;
import org.codewithmagret.rest.orders.bst.OrderNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Binary Search Tree for storing Order objects
 * based on their priority level.
 *
 * Rules:
 * - Left subtree contains orders with lower priority
 * - Right subtree contains orders with higher or equal priority
 */
public class OrderBST {

    /**
     * The root node of the tree.
     */
    OrderNode root;

    /**
     * Creates an empty Order Binary Search Tree.
     */
    public OrderBST() {
        root = null;
    }

    /**
     * Inserts an order into the BST recursively.
     *
     * @param currentNode the current node being checked
     * @param order the order to insert
     * @return the updated node
     */
    private OrderNode insert(OrderNode currentNode, Order order) {
        if (currentNode == null) {
            OrderNode newNode = new OrderNode();
            newNode.order = order;
            return newNode;
        }

        int newPriority = order.getPriorityLevel();
        int currentPriority = currentNode.order.getPriorityLevel();

        if (newPriority < currentPriority) {
            currentNode.left = insert(currentNode.left, order);
            return currentNode;
        } else {
            currentNode.right = insert(currentNode.right, order);
            return currentNode;
        }
    }

    /**
     * Inserts an order into the BST.
     *
     * @param order the order to insert
     */
    public void insert(Order order) {
        root = insert(root, order);
    }

    /**
     * Performs inorder traversal of the BST and stores the orders
     * in sorted order of priority.
     *
     * @param node the current node
     * @param sortedOrders the list to store sorted orders
     */
    public void inOrder(OrderNode node, List<Order> sortedOrders) {
        if (node == null) {
            return;
        }

        inOrder(node.left, sortedOrders);
        sortedOrders.add(node.order);
        inOrder(node.right, sortedOrders);
    }

    /**
     * Returns all orders in sorted order of priority using inorder traversal.
     *
     * @return a sorted list of orders
     */
    public List<Order> inOrder() {
        List<Order> sortedOrders = new ArrayList<>();
        inOrder(root, sortedOrders);
        return sortedOrders;
    }

    /**
     * Finds the node with the lowest priority in the BST.
     *
     * @param node the starting node
     * @return the node with the lowest priority, or null if tree is empty
     */
    public OrderNode findLowestNode(OrderNode node) {
        if (node == null) {
            return null;
        }

        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    /**
     * Finds the node with the highest priority in the BST.
     *
     * @param node the starting node
     * @return the node with the highest priority, or null if tree is empty
     */
    public OrderNode findHighestNode(OrderNode node) {
        if (node == null) {
            return null;
        }

        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    /**
     * Finds the order with the lowest priority in the BST.
     *
     * @return the order with the lowest priority, or null if tree is empty
     */
    public Order findLowest() {
        OrderNode lowestNode = findLowestNode(root);
        return lowestNode == null ? null : lowestNode.order;
    }

    /**
     * Finds the order with the highest priority in the BST.
     *
     * @return the order with the highest priority, or null if tree is empty
     */
    public Order findHighest() {
        OrderNode highestNode = findHighestNode(root);
        return highestNode == null ? null : highestNode.order;
    }
}