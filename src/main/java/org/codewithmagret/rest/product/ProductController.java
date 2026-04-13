package org.codewithmagret.rest.product;

import org.codewithmagret.rest.product.dto.ProductRequestDTO;
import org.codewithmagret.rest.product.dto.ProductResponseDTO;
import org.codewithmagret.rest.product.sort.ProductSorter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling product-related API requests.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    /**
     * Service for product-related business logic.
     */
    private final ProductService productService;

    private final ProductSorter productSorter;

    /**
     * Creates a ProductController with the given product service.
     *
     * @param productService the product service
     */
    public ProductController(ProductService productService, ProductSorter productSorter) {
        this.productService = productService;
        this.productSorter = productSorter;
    }

    /**
     * Retrieves all products.
     *
     * @return a list of all products
     */
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    /**
     * Creates a new product.
     *
     * @param request the product creation request
     * @return the created product
     */
    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO request) {
        ProductResponseDTO createdProduct = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Retrieves products sorted by the given field.
     * Supported values: price, stock
     *
     * @param by the field to sort by
     * @return a sorted list of products
     */
    @GetMapping("/sorted")
    public ResponseEntity<List<ProductResponseDTO>> getSortedProducts(@RequestParam String by) {
        return ResponseEntity.ok(productSorter.getSortedProducts(by));
    }
}