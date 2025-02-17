package com.capitole.productcatalogmanager.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@AllArgsConstructor
public class Product {

    @NotNull
    @Pattern(regexp = "^SKU\\d+$", message = "SKU must start with 'SKU' followed by numbers only")
    private String sku;
    @NotNull
    @Min(value = 0, message = "Price must be greater than zero")
    private BigDecimal price;
    private String description;
    private String category;
    private BigDecimal discount;
    private BigDecimal priceWithDiscount;

    public void applyDiscount(BigDecimal discount) {
        this.discount = discount;
        BigDecimal discountAmount = this.price.multiply(this.discount)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        this.priceWithDiscount = this.price.subtract(discountAmount);
    }

}
