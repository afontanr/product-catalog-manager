package com.capitole.productcatalogmanager.infrastructure.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductDto {

    private String sku;
    private BigDecimal price;
    private String description;
    private String category;
    private BigDecimal discount;
    private BigDecimal priceWithDiscount;


}
