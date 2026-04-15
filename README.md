# Warehouse Inventory & Order Priority System

## Project Overview

This project is a **Spring Boot REST API** designed to manage a warehouse system while demonstrating understanding of **Data Structures and Algorithms**.

The system allows:
- Managing products and customers
- Creating and managing orders
- Handling order items
- Sorting products using a **manual sorting algorithm**
- Managing order priorities using a **Binary Search Tree (BST)**

---

## Tech Stack

- Java 21+
- Spring Boot
- Spring Data JPA
- MySQL / PostgreSQL
- Maven
- Postman (for testing)

---

## Setup Instructions

### 1. Clone the repository
```
git clone https://github.com/Magret1730/warehouseInventory
cd warehouseInventory
```

### 2. Configure Database

Update `application.properties`:

```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/warehouseInventory
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.show-sql=true
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
springdoc.swagger-ui.path=/swagger
```

### 3. Run the Application

```
mvn spring-boot:run
```

---

## API Endpoints

### Product Endpoints

| Method | Endpoint | Description |
|--------|--------|-------------|
| GET | /products | Get all products |
| POST | /products | Create a product |
| GET | /products/sorted?by=price | Sort by price |
| GET | /products/sorted?by=stock | Sort by stock |

---

### Customer Endpoints

| Method | Endpoint | Description |
|--------|--------|-------------|
| GET | /customers | Get all customers |
| POST | /customers | Create a customer |
| GET | /customers/{id} | Get customer by ID |
| PUT | /customers/{id} | Update customer by ID |
| PUT | /customers/{id} | Update customer |

---

### Order Endpoints

| Method | Endpoint | Description |
|--------|--------|-------------|
| POST | /orders | Create an order |
| GET | /orders | Get all orders |

---

### BST Endpoints

| Method | Endpoint | Description |
|--------|--------|-------------|
| POST | /orders/add-to-priority-tree | Add orders to BST |
| GET | /orders/priority/inorder | Get sorted orders |
| GET | /orders/priority/highest | Get highest priority |
| GET | /orders/priority/lowest | Get lowest priority |

---

## Request & Response Examples

### Create Product

**Request**
```
{
  "name": "Laptop",
  "price": 1200.00,
  "stock": 9
}
```

---

### Create Customer

**Request**
```
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

---

### Create Order

**Request**
```
{
  "orderDate": "2026-04-14",
  "priorityLevel": 5,
  "customerId": 1,
  "items": [
    {
      "productId": 1,
      "quantity": 2
    }
  ]
}
```

---

### Sorted Products (Response)

```
[
  {
    "id": 1,
    "name": "Mouse",
    "price": 25.5
  },
  {
    "id": 2,
    "name": "Keyboard",
    "price": 45.0
  }
]
```

---

## Binary Search Tree (BST) – Theory

### Why does an inorder traversal return sorted results?

In a Binary Search Tree, the left subtree usually contains smaller values
while the right subtree contains larger values. This means values are visited
from smallest to largest, producing a sorted result.

Inorder traversal follows: **Left -> Root -> Right**

---

### What happens if values are inserted in order (1,2,3,4,5)?

The tree will not be balanced and all the values will be inserted as right children,
creating a structure like this:

```
1
 \
  2
   \
    3
     \
      4
       \
        5
```

---

### Where would you place duplicate priority values?

Duplicate priority values are placed in the **right subtree**. This is because the insertion logic
treats values that are **greater than or equal to** the current node as going to the right.
This ensures consistency in the tree structure and maintains the Binary Search Tree property.

---

## Sorting Algorithm – Theory

### How does the sorting algorithm work?

I used **Insertion Sort**.

**Example:**  
Arr = [29, 25, 3, 49, 9]

```
Steps:
1. Assume the first element is already sorted
    Sorted portion: [29]

2. Take the next element (25)
    Compare 25 with 29
    Since 25 < 29, shift 29 one position to the right
    Insert 25 into the correct position
    Array becomes: [25, 29, 3, 49, 9]

3. Take the next element (3)
    Compare 3 with 29
    Since 3 < 29, shift 29 to the right
    Compare 3 with 25
    Since 3 < 25, shift 25 to the right
    Insert 3 into the correct position
    Array becomes: [3, 25, 29, 49, 9]

4. Take the next element (49)
    Compare 49 with 29
    Since 49 > 29, it is already in the correct position
    Array remains: [3, 25, 29, 49, 9]

5. Take the next element (9)
    Compare 9 with 49
    Since 9 < 49, shift 49 to the right
    Compare 9 with 29
    Since 9 < 29, shift 29 to the right
    Compare 9 with 25
    Since 9 < 25, shift 25 to the right
    Compare 9 with 3
    Since 9 > 3, insert 9 after 3
    Array becomes: [3, 9, 25, 29, 49]

6. Continue until all elements have been inserted into their correct positions
    Final sorted array = [3, 9, 25, 29, 49]
```

---

### What is the time complexity of your algorithm?

The time complexity of Insertion Sort is:

- **Best case:** O(n) when the list is already sorted and an optimized version is used
- **Worst case:** O(n²)
- **Average case:** O(n²)

### When would your sorting algorithm perform well?

Bubble Sort performs well on:
- small datasets
- nearly sorted data
- simple learning scenarios where the goal is to understand sorting logic

### Why is your sorting algorithm ideal or not ideal for very large datasets?

Insertion Sort is not ideal for very large datasets because its worst-case and average-case
time complexity is O(n²), which makes it inefficient as the amount of data increases.
However, it is a good choice for small or nearly sorted datasets because it is simple,
easy to implement, and performs efficiently when only a few elements are out of place.

---

## System Design Questions

### Why sort in the application instead of the database?

Sorting in the application allows for:
- Custom sorting logic (e.g., multiple criteria)
- More control over sorting behavior and performance

---

### Advantage of using BST?

- Efficient retrieval of highest and lowest priority
- Sorted traversal without re-sorting

---

### Limitation of this design?

- BST can become unbalanced
- No self-balancing (like AVL or Red-Black Tree)
- Performance may degrade to O(n)

---

## AI Usage

AI tool (ChatGPT) was used to:

- Help breakdown project requirements into manageable tasks
- Assist in making BST and sorting algorithms better
- Improve documentation and README structure
- Help with PR descriptions

All generated content was reviewed, tested, and modified to fit project requirements.

---

## Javadocs
The javadocs can be found in
[Javadocs URL](https://magret1730.github.io/warehouseInventory/)

---

## Demo Video

The demo video demonstrates:
- API testing in Postman
- Product and customer creation
- Order creation
- BST traversal results
- Sorting functionality

---

## Conclusion

This project demonstrates:
- Backend API development with Spring Boot
- Data modeling with JPA
- Manual implementation of algorithms (BST & Sorting)
- Clean architecture (Controller → Service → Repository)
- Proper validation and error handling

---

This project combines real-world backend development with core computer science concepts.
