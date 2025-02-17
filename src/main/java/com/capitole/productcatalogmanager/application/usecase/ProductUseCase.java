package com.capitole.productcatalogmanager.application.usecase;

import com.capitole.productcatalogmanager.domain.model.Product;
import org.springframework.data.domain.Page;


public interface ProductUseCase {

    /**
     * Retrieves a paginated list of products, optionally filtered by category and sorted by a specified field.
     * Applies discounts to each product before returning the result.
     *
     * @param category The category to filter products by (optional).
     * @param sortBy The field to sort by (e.g., "price", "sku", "description", "category").
     * @param asc Indicates whether sorting should be in ascending order (true) or descending order (false).
     * @param page The page number to retrieve (0-based index).
     * @return A {@link Page} of {@link Product} objects with discounts applied.
     */
    Page<Product> getProducts(String category, String sortBy, boolean asc, int page);

}
