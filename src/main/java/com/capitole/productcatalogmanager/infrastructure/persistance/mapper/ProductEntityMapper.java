package com.capitole.productcatalogmanager.infrastructure.persistance.mapper;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A utility class for mapping {@link ProductEntity} objects to {@link Product} domain objects.
 * <p>
 * This class contains static methods for converting individual entities and lists of entities into
 * their corresponding domain models.
 * </p>
 */
public class ProductEntityMapper {

    private ProductEntityMapper() {
    }

    /**
     * Converts a {@link ProductEntity} to a {@link Product} domain object.
     *
     * @param entity The {@link ProductEntity} to be converted.
     * @return The corresponding {@link Product} domain object.
     */
    public static Product fromEntityToDomain(ProductEntity entity) {
        return new Product(
                entity.getSku(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getCategory(),
                null,
                null);
    }

    /**
     * Converts a list of {@link ProductEntity} objects to a list of {@link Product} domain objects.
     *
     * @param entityList The list of {@link ProductEntity} objects to be converted.
     * @return A list of {@link Product} domain objects.
     */
    public static List<Product> fromEntityListToDomainList(List<ProductEntity> entityList) {
        return entityList.stream()
                .map(ProductEntityMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    /**
     * Converts a paginated list of {@link ProductEntity} objects to a paginated list of {@link Product} domain objects.
     *
     * @param entityList The paginated list of {@link ProductEntity} objects to be converted.
     * @return A paginated list of {@link Product} domain objects.
     */
    public static Page<Product> fromEntityListToDomainList(Page<ProductEntity> entityList) {
        return entityList.map(ProductEntityMapper::fromEntityToDomain);
    }

}
