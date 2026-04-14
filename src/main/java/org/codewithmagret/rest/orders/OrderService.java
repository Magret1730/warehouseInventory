package org.codewithmagret.rest.orders;

import org.codewithmagret.rest.customer.Customer;
import org.codewithmagret.rest.customer.CustomerRepository;
import org.codewithmagret.rest.orderItem.OrderItem;
import org.codewithmagret.rest.orderItem.OrderItemRepository;
import org.codewithmagret.rest.orderItem.dto.OrderItemRequestDTO;
import org.codewithmagret.rest.orders.bst.OrderBST;
import org.codewithmagret.rest.orders.dto.OrderRequestDTO;
import org.codewithmagret.rest.product.Product;
import org.codewithmagret.rest.product.ProductRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling order-related business logic.
 * This includes creating, retrieving, updating, and deleting orders.
 */
@Service
public class OrderService {

    /**
     * Repository used for order database operations.
     */
    private final OrderRepository orderRepository;

    /**
     * Repository used for customer database operations.
     */
    private final CustomerRepository customerRepository;

    /**
     * Repository for product database operations.
     */
    private final ProductRepository productRepository;

    /**
     * Repository for order item database operations.
     */
    private final OrderItemRepository orderItemRepository;

    /**
     * Binary Search Tree used for order priority operations.
     */
    private OrderBST orderBST;

    /**
     * Creates an OrderService with required dependencies.
     *
     * @param orderRepository repository for orders
     * @param customerRepository repository for customers
     * @param productRepository repository for products
     * @param orderItemRepository repository for order items
     */
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        ProductRepository productRepository,
                        OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderBST = new OrderBST();
    }

    /**
     * Creates a new order with its associated order items.
     *
     * @param request the order creation request
     * @return the created order
     * @throws IllegalArgumentException if customer or product does not exist,
     *                                  or if request data is invalid
     */
    public Order createOrder(OrderRequestDTO request) {
        validateOrderRequest(request);

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with id: " + request.getCustomerId()
                ));

        Order order = new Order();
        order.setOrderDate(request.getOrderDate());
        order.setPriorityLevel(request.getPriorityLevel());
        order.setCustomer(customer);

        Order savedOrder = orderRepository.save(order);

        for (OrderItemRequestDTO itemRequest : request.getItems()) {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Product not found with id: " + itemRequest.getProductId()
                    ));

            if (itemRequest.getQuantity() <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0.");
            }

            if (product.getStock() < itemRequest.getQuantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product: " + product.getName()
                );
            }

            product.setStock(product.getStock() - itemRequest.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.getQuantity());

            orderItemRepository.save(orderItem);
        }

        return savedOrder;
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return a list of all orders
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves a single order by its ID.
     *
     * @param id the ID of the order
     * @return the matching order
     * @throws IllegalArgumentException if no order is found with the given ID
     */
    public Order getOrderById(Long id) {
       if (orderRepository.existsById(id)) {
           return orderRepository.findById(id).get();
       } else {
           throw new IllegalArgumentException("Order not found with id: " + id);
       }
    }

    /**
     * Updates an existing order using the provided request data.
     *
     * @param id the ID of the order to update
     * @param requestDTO the updated order request data
     * @return the updated order
     * @throws IllegalArgumentException if the order does not exist or the request is invalid
     */
    public Order updateOrder(Long id, OrderRequestDTO requestDTO) {
        validateOrderRequest(requestDTO);

        Order existingOrder = getOrderById(id);

        Customer customer = customerRepository.findById(requestDTO.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Customer not found with id: " + requestDTO.getCustomerId()
                ));

        existingOrder.setOrderDate(requestDTO.getOrderDate());
        existingOrder.setPriorityLevel(requestDTO.getPriorityLevel());
        existingOrder.setCustomer(customer);

        return orderRepository.save(existingOrder);
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the ID of the order to delete
     * @throws IllegalArgumentException if the order does not exist
     */
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    /**
     * Validates order request data before save or update operations.
     *
     * @param requestDTO the order request data to validate
     * @throws IllegalArgumentException if the request is null or contains invalid values
     */
    private void validateOrderRequest(OrderRequestDTO requestDTO) {
        if (requestDTO == null) {
            throw new IllegalArgumentException("Order request cannot be null");
        }

        if (requestDTO.getOrderDate() == null) {
            throw new IllegalArgumentException("Order date is required");
        }

        if (requestDTO.getPriorityLevel() < 1 || requestDTO.getPriorityLevel() > 10) {
            throw new IllegalArgumentException("Priority level must be between 1 and 10");
        }

        if (requestDTO.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer id is required");
        }

        if (requestDTO.getItems() == null || requestDTO.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item.");
        }
    }

    /**
     * Adds all orders from the database into the priority Binary Search Tree.
     */
    public void addOrdersToPriorityTree() {
        OrderBST newTree = new OrderBST();
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            newTree.insert(order);
        }

        this.orderBST = newTree;
    }

    /**
     * Returns the orders in ascending order of priority
     * using inorder traversal of the BST.
     *
     * @return a sorted list of orders by priority
     */
    public List<Order> getOrdersInPriorityOrder() {
        return orderBST.inOrder();
    }

    /**
     * Returns the highest-priority order from the BST.
     *
     * @return the highest-priority order
     * @throws IllegalArgumentException if the priority tree is empty
     */
    public Order getHighestPriorityOrder() {
        Order highest = orderBST.findHighest();

        if (highest == null) {
            throw new IllegalArgumentException("Priority tree is empty. Add orders to the tree first.");
        }

        return highest;
    }

    /**
     * Returns the lowest-priority order from the BST.
     *
     * @return the lowest-priority order
     * @throws IllegalArgumentException if the priority tree is empty
     */
    public Order getLowestPriorityOrder() {
        Order lowest = orderBST.findLowest();

        if (lowest == null) {
            throw new IllegalArgumentException("Priority tree is empty. Add orders to the tree first.");
        }

        return lowest;
    }
}