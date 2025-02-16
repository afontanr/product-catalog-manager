package com.capitole.productcatalogmanager.application.usecase;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductInteractor implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public List<Product> getProducts() {
        return productRepositoryPort.findAll();
    }
}
