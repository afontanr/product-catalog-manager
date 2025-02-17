package com.capitole.productcatalogmanager.infrastructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PageDto {

    private final int page;
    private final int size;
    private final long totalElements;
    private final int totalPages;
    private final List<ProductDto> content;

}
