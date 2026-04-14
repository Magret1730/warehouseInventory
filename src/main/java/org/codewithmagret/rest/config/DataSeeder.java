package org.codewithmagret.rest.config;

import org.codewithmagret.rest.customer.Customer;
import org.codewithmagret.rest.customer.CustomerRepository;
import org.codewithmagret.rest.orders.Order;
import org.codewithmagret.rest.orders.OrderRepository;
import org.codewithmagret.rest.orderItem.OrderItem;
import org.codewithmagret.rest.orderItem.OrderItemRepository;
import org.codewithmagret.rest.product.Product;
import org.codewithmagret.rest.product.ProductRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Seeds sample data into the database when the application starts.
 */
@Component
public class DataSeeder implements CommandLineRunner {

    /**
     * Repository for product operations.
     */
    private final ProductRepository productRepository;

    /**
     * Repository for customer operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Repository for order operations.
     */
    private final OrderRepository orderRepository;

    /**
     * Repository for order item operations.
     */
    private final OrderItemRepository orderItemRepository;

    /**
     * Creates a DataSeeder with required repositories.
     *
     * @param productRepository repository for products
     * @param customerRepository repository for customers
     * @param orderRepository repository for orders
     * @param orderItemRepository repository for order items
     */
    public DataSeeder(ProductRepository productRepository,
                      CustomerRepository customerRepository,
                      OrderRepository orderRepository,
                      OrderItemRepository orderItemRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    /**
     * Seeds sample data when the application starts.
     *
     * @param args application arguments
     */
    @Override
    public void run(String @NonNull ... args) {
        seedProducts();
        seedCustomers();
        seedOrders();
    }

    /**
     * Seeds sample products if none exist.
     */
    private void seedProducts() {
        if (productRepository.count() > 0) {
            return;
        }

        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setStock(10);
        product1.setPrice(1200.00);

        Product product2 = new Product();
        product2.setName("Mouse");
        product2.setStock(50);
        product2.setPrice(25.50);

        Product product3 = new Product();
        product3.setName("Keyboard");
        product3.setStock(30);
        product3.setPrice(45.00);

        Product product4 = new Product();
        product4.setName("Monitor");
        product4.setStock(15);
        product4.setPrice(300.00);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
    }

    /**
     * Seeds sample customers if none exist.
     */
    private void seedCustomers() {
        if (customerRepository.count() > 0) {
            return;
        }

        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john@example.com");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane@example.com");

        Customer customer3 = new Customer();
        customer3.setName("Alice Brown");
        customer3.setEmail("alice@example.com");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
    }

    /**
     * Seeds sample orders and order items if none exist.
     * This step is optional but included for testing convenience.
     */
    private void seedOrders() {
        if (orderRepository.count() > 0) {
            return;
        }

        if (productRepository.count() == 0 || customerRepository.count() == 0) {
            return;
        }

        Customer customer = customerRepository.findAll().get(0);
        Product product1 = productRepository.findAll().get(0);
        Product product2 = productRepository.findAll().get(1);

        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setPriorityLevel(5);
        order.setCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        OrderItem item1 = new OrderItem();
        item1.setOrder(savedOrder);
        item1.setProduct(product1);
        item1.setQuantity(1);

        OrderItem item2 = new OrderItem();
        item2.setOrder(savedOrder);
        item2.setProduct(product2);
        item2.setQuantity(2);

        orderItemRepository.save(item1);
        orderItemRepository.save(item2);

        product1.setStock(product1.getStock() - 1);
        product2.setStock(product2.getStock() - 2);

        productRepository.save(product1);
        productRepository.save(product2);
    }
}