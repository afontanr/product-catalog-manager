package com.capitole.productcatalogmanager.domain.port;

import com.capitole.productcatalogmanager.domain.model.Product;
import org.springframework.data.domain.Page;

/**
 * Port interface for accessing product data.
 * Defines the contract for retrieving products with filtering, sorting, and pagination.
 */
public interface ProductRepositoryPort {

    /**
     * Retrieves a paginated list of products, optionally filtered by category and sorted by the specified field.
     *
     * @param category The category to filter products by. If {@code null} or empty, no filtering is applied.
     * @param sortBy The field to sort the results by (e.g., "sku", "price", "description", "category").
     * @param asc {@code true} for ascending order, {@code false} for descending order.
     * @param page The page number to retrieve (zero-based index).
     * @return A {@link Page} of {@link Product} objects matching the given criteria.
     */
    Page<Product> findAll(String category, String sortBy, boolean asc, int page);

}
