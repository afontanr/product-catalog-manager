package com.capitole.productcatalogmanager.application.usecase;

import com.capitole.productcatalogmanager.domain.model.Product;

import java.util.List;

public interface ProductUseCase {

    List<Product> getProducts();

}
