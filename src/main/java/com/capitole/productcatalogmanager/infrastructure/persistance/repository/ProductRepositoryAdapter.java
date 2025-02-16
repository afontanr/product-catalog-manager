package com.capitole.productcatalogmanager.infrastructure.persistance.repository;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import com.capitole.productcatalogmanager.infrastructure.persistance.mapper.ProductEntityMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductJpaRepository productRepository;

    public ProductRepositoryAdapter(ProductJpaRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        return ProductEntityMapper.fromEntityListToDomainList(productEntityList);
    }
}
