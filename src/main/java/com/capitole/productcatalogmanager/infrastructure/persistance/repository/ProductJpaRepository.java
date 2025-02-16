package com.capitole.productcatalogmanager.infrastructure.persistance.repository;

import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

    List<ProductEntity> findAll(Sort sort);

    List<ProductEntity> findByCategory(String category, Sort sort);

}
