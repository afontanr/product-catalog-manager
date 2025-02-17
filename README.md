# Product Catalog Manager

This is a Spring Boot-based microservice for managing product catalogs.

## 🚀 How to Run

To start the application using **Docker Compose**, run:

```sh
docker-compose up --build
```

This will build and start the necessary containers.

## ⏹️ How to Stop

To stop and remove containers, volumes, and networks, run:

```sh
docker-compose down -v
```

This ensures a clean shutdown and removes any persisted data.

## How to Make a Request to the Product Endpoint
To fetch a list of products with pagination and sorting, you can make a `GET` request to `/products`

| Method | Endpoint         | Description                                      |
|--------|------------------|--------------------------------------------------|
| GET    | /products         | Retrieve a paginated list of products.           |

### Request Parameters

| Parameter | Type    | Description                                                               | Default Value |
|-----------|---------|---------------------------------------------------------------------------|---------------|
| category  | String  | A category to filter the products (e.g., "Electronics").                  | None          |
| sortBy    | String  | The field to sort the products by (e.g., "price", "sku").                 | "sku"         |
| asc       | Boolean | Whether the sorting should be ascending (`true`) or descending (`false`). | true          |
| page      | Integer | The page number to retrieve, starting from `0`.                           | 0             |

### Example Request
`GET /products?category=Electronics&sortBy=price&asc=true&page=0`

### Example Response

```json
{
    "page": 0,
    "size": 1,
    "totalElements": 1,
    "totalPages": 1,
    "content": [
        {
            "sku": "SKU0002",
            "price": 499.00,
            "description": "4K Ultra HD Smart TV, 55 inches",
            "category": "Electronics",
            "discount": 15,
            "priceWithDiscount": 424.15
        }
    ]
}
```
### Response Fields

| Field               | Description                                                                         |
|---------------------|-------------------------------------------------------------------------------------|
| `page`              | The current page number.                                                            |
| `size`              | The number of items per page.                                                       |
| `totalElements`     | The total number of items available.                                                |
| `totalPages`        | The total number of pages.                                                          |
| `content`           | A list of products on the current page. Each product contains the following fields: |
| `sku`               | The unique identifier of the product.                                               |
| `price`             | The price of the product.                                                           |
| `description`       | A description of the product.                                                       |
| `category`          | The category to which the product belongs (e.g., "Electronics").                    |
| `discount`          | The discount percentage applied to the product.                                     |
| `priceWithDiscount` | The price after applying the discount.                                              |

### Possible HTTP Status Codes

| Status Code                 | Description                                                                   |
|-----------------------------|-------------------------------------------------------------------------------|
| `200 OK`                    | The request was successful and the response body contains the requested data. |
| `400 Bad Request`           | The request was invalid, often due to missing or incorrect parameters.        |
| `500 Internal Server Error` | An unexpected error occurred on the server.                                   |


### Example `curl` Command

To make a request to the `/products` endpoint using `curl`, you can use the following command:

```bash
curl -X GET "http://localhost:8080/products?category=Electronics&sortBy=price&asc=true&page=0" -H "Content-Type: application/json"
```

## Architectural Decisions

### 1. **Hexagonal Architecture (Ports and Adapters)**

We have adopted the Hexagonal Architecture (also known as Ports and Adapters) to decouple the core business logic from external concerns, such as databases, APIs, and UI. This architecture helps in making the system more maintainable, testable, and scalable by separating different concerns into distinct layers:

- **Core Logic (Domain)**: The core business logic is placed in the center, independent of any external systems or frameworks.
- **Ports**: Interfaces that define how external systems communicate with the core business logic (e.g., ProductRepositoryPort).
- **Adapters**: Implementations of the ports, such as the `ProductRepositoryAdapter`, that interact with external technologies like databases or external APIs.

### 2. **Product Catalog Use Case**

The `ProductUseCase` defines the operations and business rules related to products. This use case is used as an entry point for interacting with product data. The main logic is implemented in the `ProductInteractor` class. It is responsible for orchestrating the operations related to products, such as retrieving products, applying discounts, and sorting/filtering based on user input.

- **ProductInteractor**: Implements the business logic and interacts with the `ProductRepositoryPort` and `DiscountCalculator`.
- **Discount Calculator**: A service used to apply discount rules to products, ensuring that the logic is encapsulated and reusable.
- **Product Repository**: The repository layer handles data access and is abstracted through the `ProductRepositoryPort` interface.

### 3. **Repository Layer and Database Interaction**

We have abstracted the database access logic using the repository pattern, which is implemented via Spring Data JPA. This allows us to use JPA repositories for common CRUD operations while maintaining flexibility for custom queries.

- **ProductRepositoryPort**: Interface representing the repository layer that exposes methods for fetching and manipulating product data.
- **ProductRepositoryAdapter**: The adapter that implements the repository interface and interacts with the database through the `ProductJpaRepository`.

### 4. **DTOs and Mappers**

To ensure that data is correctly transferred between the application layers (e.g., between the domain model and the REST controller), we use Data Transfer Objects (DTOs). These DTOs are mapped from domain models using the `ProductDtoMapper`. This approach allows us to:

- Avoid exposing domain entities directly to external consumers (e.g., front-end or external services).
- Encapsulate any necessary transformations or formatting (such as discount calculation or price formatting).

### 5. **Error Handling**

We have implemented global exception handling using `@RestControllerAdvice`. This allows us to catch and handle exceptions consistently throughout the application. Specific exceptions, such as `PropertyReferenceException` and `IllegalArgumentException`, are handled and translated into meaningful error responses for the client.

### 6. **Paginación y Ordenación**

To improve performance and user experience when working with large sets of product data, we have implemented pagination and sorting:

- **Pagination**: The `findAll` method in the `ProductRepositoryPort` accepts a page number and page size to return paginated results. Pagination details are encapsulated in the `PageDto`.
- **Sorting**: Products can be sorted based on different fields (e.g., SKU, Price, Category). Sorting is implemented using Spring Data's `Sort` and `Pageable` objects.

### 7. **Testing Strategy**

Unit tests are written for each component (use cases, mappers, repositories, etc.) to ensure the correctness of individual components. Additionally, integration tests are used to verify the behavior of the application as a whole, particularly the interaction between the layers and external systems (such as the database).

- **Mocking**: External dependencies (like the database or external services) are mocked to isolate the business logic during unit tests.
- **Integration Tests**: Full-stack integration tests are written using Spring Boot's testing tools to ensure that the system functions correctly end-to-end.

### 8. **Database Configuration**

Database connectivity and configuration are managed using HikariCP and Flyway:

- **HikariCP**: A high-performance JDBC connection pool that is configured in the `DataSourceConfig` class.
- **Flyway**: A database migration tool that is configured to automatically handle database schema changes and migrations.

By using these tools, we ensure efficient database management and smooth schema migrations without manual intervention.

