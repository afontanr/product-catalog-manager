package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

/**
 * Component responsible for calculating the highest applicable discount for a given product.
 */
@RequiredArgsConstructor
@Component
public class DiscountCalculator {

    private final List<DiscountRule> discountRules;

    /**
     * Calculates the highest discount applicable to the given product based on the available discount rules.
     *
     * @param product The {@link Product} for which the discount is calculated.
     * @return A {@link BigDecimal} representing the highest discount amount. Returns {@code BigDecimal.ZERO} if no discounts apply.
     */
    public BigDecimal calculateDiscount(Product product) {
        return discountRules.stream()
                .map(rule -> rule.apply(product))
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);
    }

}
