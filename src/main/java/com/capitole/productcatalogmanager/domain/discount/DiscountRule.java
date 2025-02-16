package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;

import java.math.BigDecimal;

public interface DiscountRule {

    BigDecimal apply(Product product);

}
