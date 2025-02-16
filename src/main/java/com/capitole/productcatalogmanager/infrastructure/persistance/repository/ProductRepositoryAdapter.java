package com.capitole.productcatalogmanager.infrastructure.persistance.repository;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import com.capitole.productcatalogmanager.infrastructure.persistance.mapper.ProductEntityMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll(String category, String sortBy, boolean asc) {
        Sort sort = getSort(sortBy, asc);
        List<ProductEntity> productEntityList = getProducts(category, sort);

        return ProductEntityMapper.fromEntityListToDomainList(productEntityList);
    }

    private Sort getSort(String sortBy, boolean asc) {
        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        return Sort.by(direction, sortBy);
    }

    private List<ProductEntity> getProducts(String category, Sort sort) {
        if (StringUtils.hasText(category)) {
            return productRepository.findByCategory(category, sort);
        }
        return productRepository.findAll(sort);
    }
}
