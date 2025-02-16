package com.capitole.productcatalogmanager.domain.port;

import com.capitole.productcatalogmanager.domain.model.Product;

import java.util.List;

public interface ProductRepositoryPort {

    List<Product> findAll(String category, String sortBy, boolean asc);

}
