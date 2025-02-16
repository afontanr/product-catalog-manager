package com.capitole.productcatalogmanager.domain.discount;


import com.capitole.productcatalogmanager.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ElectronicsDiscountRule implements DiscountRule {

    @Override
    public BigDecimal apply(Product product) {
        return "Electronics".equalsIgnoreCase(product.getCategory())
                ? BigDecimal.valueOf(15) : BigDecimal.ZERO;
    }

}


