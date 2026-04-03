package org.codewithmagret.rest.orderItem;

import jakarta.persistence.*;
import org.codewithmagret.rest.order.Order;
import org.codewithmagret.rest.product.Product;

/**
 * Represents an item in an order, including the product name, quantity, and price.
 */
@Entity
@Table(name = "order_items")
public class OrderItem {
    /**
     * The unique identifier for the order item.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The quantity of the product ordered.
     */
    private int quantity;

    /**
     * Many-to-One relationship between OrderItem and Product
     * An order item is associated with one product, but a product can be associated with many order items.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Many-to-One relationship between OrderItem and Order
     * An order can have many order items, but an order item belongs to one order.
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * Default constructor for JPA.
     */
    public OrderItem() {
    }

    /**
     * Parameterized constructor for creating an order item with specified quantity, product, and order.
     *
     * @param quantity the quantity of the product ordered
     * @param product the product associated with the order item
     * @param order the order to which this item belongs
     */
    public OrderItem(int quantity, Product product, Order order) {
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    /**
     * Gets the unique identifier of the order item.
     *
     * @return the order item ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the quantity of the product ordered.
     *
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product ordered.
     *
     * @param quantity the new quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the order item.
     *
     * @return a string containing the product name, quantity, and price
     */
    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", product=" + product.getName() +
                ", price=" + product.getPrice() +
                '}';
    }
}