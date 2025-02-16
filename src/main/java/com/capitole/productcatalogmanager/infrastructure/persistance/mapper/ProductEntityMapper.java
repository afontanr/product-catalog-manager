package com.capitole.productcatalogmanager.infrastructure.persistance.mapper;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;


public class ProductEntityMapper {

    private ProductEntityMapper() {
    }

    public static Product fromEntityToDomain(ProductEntity entity) {
        return new Product(
                entity.getSku(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getCategory(),
                null,
                null);
    }

    public static List<Product> fromEntityListToDomainList(List<ProductEntity> entityList) {
        return entityList.stream()
                .map(ProductEntityMapper::fromEntityToDomain)
                .collect(Collectors.toList());
    }

}
