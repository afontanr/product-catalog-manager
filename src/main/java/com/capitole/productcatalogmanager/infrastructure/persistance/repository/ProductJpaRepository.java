package com.capitole.productcatalogmanager.infrastructure.persistance.repository;

import com.capitole.productcatalogmanager.infrastructure.persistance.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductJpaRepository extends JpaRepository<ProductEntity, Integer> {

    Page<ProductEntity> findAll(Pageable pageable);

    Page<ProductEntity> findByCategory(String category, Pageable pageable);

}
