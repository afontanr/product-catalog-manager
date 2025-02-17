package com.capitole.productcatalogmanager.infrastructure.persistance.repository;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import com.capitole.productcatalogmanager.infrastructure.config.DataSourceProperties;
import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import com.capitole.productcatalogmanager.infrastructure.persistance.mapper.ProductEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Adapter class that implements the {@link ProductRepositoryPort} interface, providing
 * access to the underlying {@link ProductJpaRepository} for CRUD operations related to {@link ProductEntity}.
 * <p>
 * This class is responsible for adapting the {@link ProductJpaRepository} and converting
 * the {@link ProductEntity} results into {@link Product} domain objects while supporting pagination
 * and sorting capabilities.
 * </p>
 */
@RequiredArgsConstructor
@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productRepository;
    private final DataSourceProperties dataSourceProperties;

    /**
     * Retrieves a paginated list of products, optionally filtered by category and sorted by the given field.
     *
     * @param category The category by which the products will be filtered (can be {@code null} for no filtering).
     * @param sortBy The field to sort the products by.
     * @param asc {@code true} for ascending order, {@code false} for descending order.
     * @param page The page number (zero-based index) to retrieve.
     * @return A {@link Page} containing the {@link Product} domain objects.
     */
    @Override
    public Page<Product> findAll(String category, String sortBy, boolean asc, int page) {
        Sort sort = getSort(sortBy, asc);
        Pageable pageable = getPageRequest(page, sort);
        Page<ProductEntity> productEntityList = getProducts(category, pageable);

        return ProductEntityMapper.fromEntityListToDomainList(productEntityList);
    }

    /**
     * Constructs a {@link Sort} object based on the specified field and sorting order.
     *
     * @param sortBy The field to sort by.
     * @param asc {@code true} for ascending order, {@code false} for descending order.
     * @return The corresponding {@link Sort} object.
     */
    private Sort getSort(String sortBy, boolean asc) {
        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        return Sort.by(direction, sortBy);
    }

    /**
     * Retrieves a {@link Page} of {@link ProductEntity} objects, optionally filtered by category.
     *
     * @param category The category by which the products will be filtered (can be {@code null} for no filtering).
     * @param pageable The pagination and sorting information.
     * @return A {@link Page} containing the {@link ProductEntity} objects.
     */
    private Page<ProductEntity> getProducts(String category, Pageable pageable) {
        if (StringUtils.hasText(category)) {
            return productRepository.findByCategory(category, pageable);
        }
        return productRepository.findAll(pageable);
    }

    /**
     * Creates a {@link Pageable} object for pagination based on the page number and sorting information.
     *
     * @param page The page number (zero-based index).
     * @param sort The sorting information.
     * @return The corresponding {@link Pageable} object.
     */
    private Pageable getPageRequest(int page, Sort sort) {
        return PageRequest.of(page, dataSourceProperties.getPageSize(), sort);
    }
}
