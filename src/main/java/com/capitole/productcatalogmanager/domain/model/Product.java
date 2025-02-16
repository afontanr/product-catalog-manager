package com.capitole.productcatalogmanager.domain.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

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

}
