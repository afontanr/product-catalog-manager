package com.capitole.productcatalogmanager.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import com.capitole.productcatalogmanager.domain.discount.*;
import com.capitole.productcatalogmanager.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DiscountCalculatorTest {

    private DiscountCalculator discountCalculator;

    @BeforeEach
    public void setUp() {
        List<DiscountRule> discountRules = List.of(
                new ElectronicsDiscountRule(),
                new HomeKitchenDiscountRule(),
                new SkuEndsIn5DiscountRule()
        );
        discountCalculator = new DiscountCalculator(discountRules);
    }


    @Test
    public void testElectronicsDiscount() {
        Product product = new Product("SKU0001", new BigDecimal("100.00"),
                "Sample Product", "Electronics", null, null);

        BigDecimal discount = discountCalculator.calculateDiscount(product);

        assertEquals(new BigDecimal("15"), discount, "Expected a 15% discount for Electronics category.");
    }


    @Test
    public void testHomeKitchenDiscount() {
        Product product = new Product("SKU0002", new BigDecimal("100.00"),
                "Sample Product", "Home & Kitchen", null, null);

        BigDecimal discount = discountCalculator.calculateDiscount(product);

        assertEquals(new BigDecimal("25"), discount, "Expected a 25% discount for Home & Kitchen category.");
    }


    @Test
    public void testSkuEndsIn5Discount() {
        Product product = new Product("SKU0005", new BigDecimal("100.00"),
                "Sample Product", "Electronics", null, null);

        BigDecimal discount = discountCalculator.calculateDiscount(product);

        assertEquals(new BigDecimal("30"), discount, "Expected a 30% discount when SKU ends in '5'.");
    }


    @Test
    public void testNoDiscount() {
        Product product = new Product("SKU0003", new BigDecimal("100.00"),
                "Sample Product", "Other", null, null);

        BigDecimal discount = discountCalculator.calculateDiscount(product);

        assertEquals(BigDecimal.ZERO, discount, "Expected no discount for a category with no discount rules.");
    }


    @Test
    public void testMultipleDiscountsChooseMax() {
        // Arrange
        Product product = new Product("SKU0005", new BigDecimal("100.00"),
                "Sample Product", "Home & Kitchen", null, null);

        BigDecimal discount = discountCalculator.calculateDiscount(product);

        assertEquals(new BigDecimal("30"), discount, "Expected the highest discount (30%) when multiple rules apply.");
    }
}
