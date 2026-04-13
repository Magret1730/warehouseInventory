package org.codewithmagret.rest.product.sort;

import org.codewithmagret.rest.product.Product;

import java.util.List;

/**
 * Provides manual sorting methods for Product objects.
 * This class uses insertion sort and does not rely on any built-in sorting utilities.
 */
public class ProductSorter {

    /**
     * Sorts a list of products by price in ascending order using insertion sort.
     *
     * @param products the list of products to sort
     */
    public void sortByPrice(List<Product> products) {
        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getPrice() > current.getPrice()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }
    }

    /**
     * Sorts a list of products by stock in ascending order using insertion sort.
     *
     * @param products the list of products to sort
     */
    public void sortByStock(List<Product> products) {
        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getStock() > current.getStock()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }
    }
}
