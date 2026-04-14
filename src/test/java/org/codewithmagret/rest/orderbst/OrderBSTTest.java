package org.codewithmagret.rest.orderbst;

import org.codewithmagret.rest.orders.Order;
import org.codewithmagret.rest.orders.bst.OrderBST;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for OrderBST.
 */
public class OrderBSTTest {

    /**
     * Tests inorder traversal returns orders sorted by priority.
     */
    @Test
    void testInorderTraversalReturnsSortedOrders() {
        OrderBST bst = new OrderBST();

        Order o1 = new Order();
        o1.setPriorityLevel(5);

        Order o2 = new Order();
        o2.setPriorityLevel(2);

        Order o3 = new Order();
        o3.setPriorityLevel(8);

        Order o4 = new Order();
        o4.setPriorityLevel(1);

        bst.insert(o1);
        bst.insert(o2);
        bst.insert(o3);
        bst.insert(o4);

        List<Order> sortedOrders = bst.inOrder();

        assertEquals(4, sortedOrders.size());
        assertEquals(1, sortedOrders.get(0).getPriorityLevel());
        assertEquals(2, sortedOrders.get(1).getPriorityLevel());
        assertEquals(5, sortedOrders.get(2).getPriorityLevel());
        assertEquals(8, sortedOrders.get(3).getPriorityLevel());
    }

    /**
     * Tests highest and lowest priority orders are returned correctly.
     */
    @Test
    void testFindHighestAndLowest() {
        OrderBST bst = new OrderBST();

        Order o1 = new Order();
        o1.setPriorityLevel(5);

        Order o2 = new Order();
        o2.setPriorityLevel(2);

        Order o3 = new Order();
        o3.setPriorityLevel(8);

        Order o4 = new Order();
        o4.setPriorityLevel(1);

        bst.insert(o1);
        bst.insert(o2);
        bst.insert(o3);
        bst.insert(o4);

        Order highest = bst.findHighest();
        Order lowest = bst.findLowest();

        assertNotNull(highest);
        assertNotNull(lowest);
        assertEquals(8, highest.getPriorityLevel());
        assertEquals(1, lowest.getPriorityLevel());
    }
}