package org.codewithmagret.rest.product;

import org.codewithmagret.rest.product.dto.ProductRequestDTO;
import org.codewithmagret.rest.product.dto.ProductResponseDTO;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Service class responsible for handling business logic related to Products.
 * Includes CRUD operations and manual sorting logic.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

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
    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        validateProduct(requestDTO);

        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setStock(requestDTO.getStock());

        // Step 1: Save entity
        Product savedProduct = productRepository.save(product);

        // Step 2: Convert to DTO
        return mapToResponseDTO(savedProduct);
    }

    /**
     * Retrieves all products and optionally sorts them
     * by price or stock in ascending order.
     *
     * @return the list of products
     * @throws IllegalArgumentException if the sort parameter is invalid
     */
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id product ID
     * @return found Product
     */
    public Product getProductById(Long id) {
        if (productRepository.findById(id).isPresent()) {
            return productRepository.findById(id).get();
        }  else {
            throw new IllegalArgumentException("Product not found with id: " + id);
        }
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
     * Sorts products by price using Bucket Sort.
     *
     * Steps:
     * 1. Determine number of buckets using square root of array length
     * 2. Find maximum price
     * 3. Place each product into its correct bucket
     * 4. Sort each bucket
     * 5. Merge all buckets back into the original list
     *
     * @param products the list of products to sort by price
     */
    private void sortByPrice(List<Product> products) {
        if (products == null || products.size() <= 1) {
            throw new IllegalArgumentException("No products found");
        }

        int length = products.size();
        System.out.println("length: " + length);
        int numOfBuckets = (int) Math.ceil(Math.sqrt(length));
        System.out.println("numOfBuckets: " + numOfBuckets);

        double max = products.get(0).getPrice();
        for (Product product : products) {
            if (product.getPrice() > max) {
                max = product.getPrice();
            }
        }
        System.out.println("max: " + max);

        List<List<Product>> buckets = new ArrayList<>();
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
        System.out.println("buckets: " + buckets);

        for (Product product : products) {
            System.out.println("BucketPRice" + product.getPrice());
            int bucketNum = (int) Math.ceil((product.getPrice() * numOfBuckets) / max);
            if (bucketNum == 0) {
                bucketNum = 1;
            }
            buckets.get(bucketNum - 1).add(product);
        }
        System.out.println("Add buckets: " + buckets);

        for (List<Product> bucket : buckets) {
            bucket.sort(Comparator.comparingDouble(Product::getPrice));
            System.out.println("Sorrt bucket: " + bucket);
        }

        int index = 0;
        for (List<Product> bucket : buckets) {
            for (Product product : bucket) {
                products.set(index++, product);
            }
        }
    }

    /**
     * Sorts products by stock using Bucket Sort.
     *
     * Steps:
     * 1. Determine number of buckets using square root of array length
     * 2. Find maximum stock
     * 3. Place each product into its correct bucket
     * 4. Sort each bucket
     * 5. Merge all buckets back into the original list
     *
     * @param products the list of products to sort by stock
     */
    private void sortByStock(List<Product> products) {
        if (products == null || products.size() <= 1) {
            throw new IllegalArgumentException("No products found");
        }

        int length = products.size();
        int numOfBuckets = (int) Math.ceil(Math.sqrt(length));

        int max = products.get(0).getStock();
        for (Product product : products) {
            if (product.getStock() > max) {
                max = product.getStock();
            }
        }

        List<List<Product>> buckets = new ArrayList<>();
        for (int i = 0; i < numOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        for (Product product : products) {
            int bucketNum = (int) Math.ceil(((double) product.getStock() * numOfBuckets) / max);
            if (bucketNum == 0) {
                bucketNum = 1;
            }
            buckets.get(bucketNum - 1).add(product);
        }

        for (List<Product> bucket : buckets) {
            bucket.sort(Comparator.comparingInt(Product::getStock));
        }

        int index = 0;
        for (List<Product> bucket : buckets) {
            for (Product product : bucket) {
                products.set(index++, product);
            }
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
                product.getStock(),
                (int) product.getPrice()

        );
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