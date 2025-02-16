package com.capitole.productcatalogmanager.domain.discount;

import com.capitole.productcatalogmanager.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DiscountCalculator {

    private final List<DiscountRule> discountRules;

    public BigDecimal calculateDiscount(Product product) {
        return discountRules.stream()
                .map(rule -> rule.apply(product))
                .max(Comparator.naturalOrder())
                .orElse(BigDecimal.ZERO);
    }

}
