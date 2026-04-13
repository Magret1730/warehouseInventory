package org.codewithmagret.rest.product;

import org.codewithmagret.rest.product.dto.ProductRequestDTO;
import org.codewithmagret.rest.product.sort.ProductSorter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for handling business logic related to Products.
 * Includes CRUD operations and manual sorting logic.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;
    ProductSorter sorter = new ProductSorter();

    /**
     * Constructor-based dependency injection for ProductRepository.
     *
     * @param productRepository repository for product data access
     */
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product after validating input data.
     *
     * @param requestDTO product request data
     * @return saved Product entity
     */
    public Product createProduct(ProductRequestDTO requestDTO) {
        validateProduct(requestDTO);

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());

        return productRepository.save(product);
    }

    /**
     * Retrieves all products and optionally sorts them
     * by price or stock in ascending order.
     *
     * @param sort the sorting field, either "price" or "stock"
     * @return the list of products
     * @throws IllegalArgumentException if the sort parameter is invalid
     */
    public List<Product> getAllProducts(String sort) {
        List<Product> products = productRepository.findAll();

        if (sort == null || sort.isBlank()) {
            return products;
        }

        if (sort.equalsIgnoreCase("price")) {
            sorter.sortByPrice(products);
        } else if (sort.equalsIgnoreCase("stock")) {
            sorter.sortByStock(products);
        } else {
            throw new IllegalArgumentException("Invalid sort parameter. Use 'price' or 'stock'.");
        }

        return products;
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id product ID
     * @return found Product
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    /**
     * Updates an existing product.
     *
     * @param id product ID
     * @param requestDTO updated product data
     * @return updated Product
     */
    public Product updateProduct(Long id, ProductRequestDTO requestDTO) {
        validateProduct(requestDTO);

        Product product = getProductById(id);
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());

        return productRepository.save(product);
    }

    /**
     * Deletes a product by ID.
     *
     * @param id product ID
     */
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    /**
     * Sorts products by price using Insertion Sort.
     *
     * @return sorted list of products by price (ascending)
     */
    public List<Product> sortProductsByPrice() {
        List<Product> products = new ArrayList<>(productRepository.findAll());

        for (int i = 1; i < products.size(); i++) {
            Product current = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getPrice() > current.getPrice()) {
                products.set(j + 1, products.get(j));
                j--;
            }

            products.set(j + 1, current);
        }

        return products;
    }

    /**
     * Validates product input data.
     *
     * @param requestDTO product data to validate
     */
    private void validateProduct(ProductRequestDTO requestDTO) {
        if (requestDTO.getName() == null || requestDTO.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }

        if (requestDTO.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        if (requestDTO.getStock() < 1) {
            throw new IllegalArgumentException("Product stock cannot be empty");
        }
    }
}