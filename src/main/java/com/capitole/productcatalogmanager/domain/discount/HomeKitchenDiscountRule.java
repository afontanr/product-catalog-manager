package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class HomeKitchenDiscountRule implements DiscountRule {

    @Override
    public BigDecimal apply(Product product) {
        return "Home & Kitchen".equalsIgnoreCase(product.getCategory())
                ? BigDecimal.valueOf(25) : BigDecimal.ZERO;
    }
}

