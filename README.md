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

Duplicate priority values are placed in the right subtree. This is because the insertion logic
treats values that are greater than or equal to the current node as going to the right.
This ensures consistency in the tree structure and maintains the Binary Search Tree property.

---

## Sorting Algorithm – Theory

### How does the sorting algorithm work?

I used **Bucket Sort**.
**Example:**
Arr = [29, 25, 3, 49, 9]

```
Steps:
1. Determine the number of buckets
    a. Calculate the length of the input array (n)
        Length = 5
    b. Calculate the square root of the length of the array to determine the number of buckets
        Number of buckets = ceil(sqrt(5)) = 3
2. Create empty buckets
    Bucket 1: []
    Bucket 2: []
    Bucket 3: []
3. Find the maximum value in the array
    Max value = 49
4. Calculate which element goes into which bucket
    a. For each element in the input array, calculate the bucket index using the formula:
        Bucket index = ceil((element * number of buckets) / max value)
    b. Place the element into the corresponding bucket based on the calculated index
        29 -> Bucket index = ceil((29 * 3) / 49) = 2 -> Bucket 2: [29]
        25 -> Bucket index = ceil((25 * 3) / 49) = 2 -> Bucket 2: [29, 25]
        3 -> Bucket index = ceil((3 * 3) / 49) = 1 -> Bucket 1: [3]
        49 -> Bucket index = ceil((49 * 3) / 49) = 3 -> Bucket 3: [49]
        9 -> Bucket index = ceil((9 * 3) / 49) = 1 -> Bucket 1: [3, 9]
    Buckets after distribution:
    Bucket 1: [3, 9]
    Bucket 2: [29, 25]
    Bucket 3: [49]
5. Sort each bucket using a sorting algorithm (e.g., Insertion Sort)
    a. Sort Bucket 1: [3, 9] -> [3, 9]
    b. Sort Bucket 2: [29, 25] -> [25, 29]
    c. Sort Bucket 3: [49] -> [49]
6. Merge all buckets by Concatenating the sorted buckets to get the final sorted array
    Final sorted array = [3, 9] + [25, 29] + [49] = [3, 9, 25, 29, 49]
```

---

### What is the time complexity of your algorithm?

The time complexity of Bubble Sort is:

- **Best case:** O(n) when the list is already sorted and an optimized version is used
- **Worst case:** O(n²)
- **Average case:** O(n²)

### When would your sorting algorithm perform well?

Bubble Sort performs well on:
- small datasets
- nearly sorted data
- simple learning scenarios where the goal is to understand sorting logic

### Why is your sorting algorithm ideal or not ideal for very large datasets?

Bubble Sort is **not ideal for very large datasets** because it has a time complexity of O(n²),
which makes it inefficient when the amount of data grows. It is better suited for small datasets
or educational purposes because it is simple to understand and implement.

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
`github.io/Magret1730/warehouseInventory`

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