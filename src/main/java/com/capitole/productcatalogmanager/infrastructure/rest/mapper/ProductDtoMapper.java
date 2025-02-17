package com.capitole.productcatalogmanager.infrastructure.rest.mapper;

import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.infrastructure.rest.dto.PageDto;
import com.capitole.productcatalogmanager.infrastructure.rest.dto.ProductDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class responsible for converting {@link Product} domain objects to {@link ProductDto} data transfer objects (DTOs).
 * <p>
 * This class provides utility methods to map individual {@link Product} objects or lists of {@link Product}
 * objects into their corresponding {@link ProductDto} representations.
 * </p>
 */
public class ProductDtoMapper {

    private ProductDtoMapper() {
    }

    /**
     * Converts a {@link Product} domain object to a {@link ProductDto}.
     *
     * @param product The {@link Product} domain object to be converted.
     * @return A {@link ProductDto} object representing the provided product.
     */
    public static ProductDto fromDomainToDto(Product product) {
        return new ProductDto(
                product.getSku(),
                product.getPrice(),
                product.getDescription(),
                product.getCategory(),
                product.getDiscount(),
                product.getPriceWithDiscount());
    }

    /**
     * Converts a list of {@link Product} domain objects to a list of {@link ProductDto} objects.
     *
     * @param productList The list of {@link Product} domain objects to be converted.
     * @return A list of {@link ProductDto} objects representing the provided product list.
     */
    public static List<ProductDto> fromDomainListToDtoList(List<Product> productList) {
        return productList.stream()
                .map(ProductDtoMapper::fromDomainToDto)
                .collect(Collectors.toList());
    }

    /**
     * Converts a {@link Page} of {@link Product} domain objects to a {@link PageDto}.
     * <p>
     * This method is used to convert paginated results from the domain to the corresponding DTO representation,
     * including pagination details such as the current page, page size, total elements, and total pages.
     * </p>
     *
     * @param productList The {@link Page} of {@link Product} domain objects to be converted.
     * @return A {@link PageDto} object representing the paginated list of products.
     */
    public static PageDto fromDomainListToDtoList(Page<Product> productList) {
        return new PageDto(
                productList.getNumber(),
                productList.getSize(),
                productList.getTotalElements(),
                productList.getTotalPages(),
                fromDomainListToDtoList(productList.getContent()));
    }

}
