package com.capitole.productcatalogmanager.application.usecase;

import com.capitole.productcatalogmanager.domain.discount.DiscountCalculator;
import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class ProductInteractor implements ProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final DiscountCalculator discountCalculator;

    @Override
    public Page<Product> getProducts(String category, String sortBy, boolean asc, int page) {
        Page<Product> products = productRepositoryPort.findAll(category, sortBy, asc, page);
        products.forEach(this::applyDiscount);
        return products;
    }

    private void applyDiscount(Product product) {
        BigDecimal discount = discountCalculator.calculateDiscount(product);
        product.applyDiscount(discount);
    }
}
