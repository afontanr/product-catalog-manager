package com.capitole.productcatalogmanager.infrastructure.rest.mapper;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.rest.dto.ProductDto;

import java.util.List;
import java.util.stream.Collectors;

public class ProductDtoMapper {

    private ProductDtoMapper() {
    }

    public static ProductDto fromDomainToDto(Product product) {
        return new ProductDto(product.getSku(), product.getPrice(), product.getDescription(), product.getCategory());
    }

    public static List<ProductDto> fromDomainListToDtoList(List<Product> productList) {
        return productList.stream()
                .map(ProductDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
    }
}
