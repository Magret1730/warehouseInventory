package org.codewithmagret.rest.product;

import org.codewithmagret.rest.product.Product;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for ProductSorter.
 */
public class ProductTest {

    /**
     * Tests that products are sorted by price in ascending order.
     */
    @Test
    void testSortByPrice() {
        List<Product> products = new ArrayList<>();

        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setPrice(1200.00);
        p1.setStock(10);

        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setPrice(25.50);
        p2.setStock(50);

        Product p3 = new Product();
        p3.setName("Keyboard");
        p3.setPrice(45.00);
        p3.setStock(30);

        products.add(p1);
        products.add(p2);
        products.add(p3);

        ProductService.sortByPrice(products);

        assertEquals("Mouse", products.get(0).getName());
        assertEquals("Keyboard", products.get(1).getName());
        assertEquals("Laptop", products.get(2).getName());
    }

    /**
     * Tests that products are sorted by stock in ascending order.
     */
    @Test
    void testSortByStock() {
        List<Product> products = new ArrayList<>();

        Product p1 = new Product();
        p1.setName("Laptop");
        p1.setPrice(1200.00);
        p1.setStock(10);

        Product p2 = new Product();
        p2.setName("Mouse");
        p2.setPrice(25.50);
        p2.setStock(50);

        Product p3 = new Product();
        p3.setName("Keyboard");
        p3.setPrice(45.00);
        p3.setStock(30);

        products.add(p1);
        products.add(p2);
        products.add(p3);

        ProductService.sortByStock(products);

        assertEquals("Laptop", products.get(0).getName());
        assertEquals("Keyboard", products.get(1).getName());
        assertEquals("Mouse", products.get(2).getName());
    }
}
