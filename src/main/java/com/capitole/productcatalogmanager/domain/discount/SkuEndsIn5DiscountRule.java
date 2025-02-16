package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SkuEndsIn5DiscountRule implements DiscountRule {

    @Override
    public BigDecimal apply(Product product) {
        return product.getSku().endsWith("5")
                ? BigDecimal.valueOf(30) : BigDecimal.ZERO;
    }
}

