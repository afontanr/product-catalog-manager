package com.capitole.productcatalogmanager.infrastructure.rest.controller;

import com.capitole.productcatalogmanager.application.usecase.ProductUseCase;
import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.rest.dto.PageDto;
import com.capitole.productcatalogmanager.infrastructure.rest.mapper.ProductDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles HTTP requests related to product information.
 * <p>
 * This controller provides an endpoint for retrieving a paginated list of products, with optional filters such as
 * category, sorting, and pagination options. The response is returned as a {@link PageDto}, containing the list of
 * products in the desired format and pagination details.
 * </p>
 */
@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductUseCase productUseCase;

    /**
     * Retrieves a paginated list of products, with optional filtering by category, sorting, and pagination.
     *
     * @param category The category by which to filter the products (optional).
     * @param sortBy The field by which to sort the products (default is "sku").
     * @param page The page number for pagination (default is 0).
     * @param asc Boolean value indicating the sorting order (default is true for ascending order).
     * @return A {@link ResponseEntity} containing a {@link PageDto} that wraps the list of products.
     */
    @GetMapping("/products")
    public ResponseEntity<PageDto> findAll(@RequestParam(required = false) String category,
                                           @RequestParam(defaultValue = "sku") String sortBy,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "true") boolean asc) {

        Page<Product> productList = productUseCase.getProducts(category, sortBy, asc, page);

        return ResponseEntity.ok(ProductDtoMapper.fromDomainListToDtoList(productList));
    }

}
