package com.capitole.productcatalogmanager.infrastructure.rest.controller;

import com.capitole.productcatalogmanager.application.usecase.ProductUseCase;
import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.rest.dto.ProductDto;
import com.capitole.productcatalogmanager.infrastructure.rest.mapper.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductUseCase productUseCase;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> findAll(@RequestParam(required = false) String category,
                                                    @RequestParam(defaultValue = "sku") String sortBy,
                                                    @RequestParam(defaultValue = "true") boolean asc) {

        List<Product> productList = productUseCase.getProducts(category, sortBy, asc);

        return ResponseEntity.ok(ProductDtoMapper.fromDomainListToDtoList(productList));
    }

}
