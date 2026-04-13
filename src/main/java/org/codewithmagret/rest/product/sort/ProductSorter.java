package org.codewithmagret.rest.product.sort;

import org.codewithmagret.rest.product.Product;
import org.codewithmagret.rest.product.ProductRepository;
import org.codewithmagret.rest.product.dto.ProductResponseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides manual sorting methods for Product objects.
 * This class uses insertion sort and does not rely on any built-in sorting utilities.
 */
public class ProductSorter {

    private final ProductRepository productRepository;

    public ProductSorter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

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

    /**
     * Retrieves sorted products based on the specified field.
     * @param by the field to sort by (price or stock)
     * @return a list of sorted products as ProductResponseDTOs
     */
    public List<ProductResponseDTO> getSortedProducts(String by) {

        if (by == null || by.isBlank()) {
            throw new IllegalArgumentException("Sort parameter is required");
        }

        List<Product> products = new ArrayList<>(productRepository.findAll());

        if (by.equalsIgnoreCase("price")) {
            sortByPrice(products);
        } else if (by.equalsIgnoreCase("stock")) {
            sortByStock(products);
        } else {
            throw new IllegalArgumentException("Invalid sort parameter. Use 'price' or 'stock'.");
        }

        return products.stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Maps a Product entity to a ProductResponseDTO.
     * @param product the Product entity to map
     * @return a ProductResponseDTO containing the product's ID, name, price, and stock
     */
    private ProductResponseDTO mapToResponseDTO(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                (int) product.getPrice(),
                product.getStock()
        );
    }
}
