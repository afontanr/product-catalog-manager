package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;

import java.math.BigDecimal;

/**
 * Interface representing a discount rule that can be applied to a product.
 */
public interface DiscountRule {

    /**
     * Applies the discount rule to the given product and returns the discounted amount.
     *
     * @param product The {@link Product} to which the discount should be applied.
     * @return A {@link BigDecimal} representing the discount amount.
     */
    BigDecimal apply(Product product);

}
