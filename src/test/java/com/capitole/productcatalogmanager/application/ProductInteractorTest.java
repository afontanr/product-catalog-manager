package com.capitole.productcatalogmanager.application;

import com.capitole.productcatalogmanager.application.usecase.ProductInteractor;
import com.capitole.productcatalogmanager.domain.discount.DiscountCalculator;
import com.capitole.productcatalogmanager.domain.model.Product;
import com.capitole.productcatalogmanager.domain.port.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductInteractorTest {

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private DiscountCalculator discountCalculator;

    @InjectMocks
    private ProductInteractor productInteractor;

    @Test
    void getProducts_ShouldApplyDiscountToAllProducts() {
        Product mockProduct = mock(Product.class);
        Page<Product> mockPage = new PageImpl<>(List.of(mockProduct));

        when(productRepositoryPort.findAll(
                anyString(),
                anyString(),
                anyBoolean(),
                anyInt()
        )).thenReturn(mockPage);

        when(discountCalculator.calculateDiscount(mockProduct))
                .thenReturn(new BigDecimal("10.00"));

        Page<Product> result = productInteractor.getProducts(
                "electronics",
                "price",
                true,
                0
        );

        verify(productRepositoryPort).findAll(
                eq("electronics"),
                eq("price"),
                eq(true),
                eq(0)
        );

        verify(discountCalculator).calculateDiscount(mockProduct);

        verify(mockProduct).applyDiscount(new BigDecimal("10.00"));

        assertEquals(1, result.getTotalElements());
    }

    @Test
    void getProducts_WithEmptyPageShouldNotApplyDiscounts() {
        Page<Product> emptyPage = new PageImpl<>(Collections.emptyList());

        when(productRepositoryPort.findAll(
                anyString(),
                anyString(),
                anyBoolean(),
                anyInt()
        )).thenReturn(emptyPage);

        Page<Product> result = productInteractor.getProducts(
                "books",
                "name",
                false,
                1
        );

        assertTrue(result.isEmpty());
        verifyNoInteractions(discountCalculator);
    }

    @Test
    void getProductsShouldHandleNullParameters() {
        Product mockProduct = mock(Product.class);
        Page<Product> mockPage = new PageImpl<>(List.of(mockProduct));

        when(productRepositoryPort.findAll(
                isNull(),
                isNull(),
                anyBoolean(),
                anyInt()
        )).thenReturn(mockPage);

        Page<Product> result = productInteractor.getProducts(
                null,  // category
                null,  // sortBy
                true,
                0
        );

        verify(productRepositoryPort).findAll(
                null,
                null,
                true,
                0
        );
        verify(discountCalculator).calculateDiscount(mockProduct);
    }

    @Test
    void getProductsShouldMaintainPaginationMetadata() {
        List<Product> products = Collections.nCopies(5, mock(Product.class));
        Page<Product> mockPage = new PageImpl<>(
                products,
                PageRequest.of(0, 5, Sort.by("price")),
                10
        );

        when(productRepositoryPort.findAll(
                any(), any(), anyBoolean(), anyInt()
        )).thenReturn(mockPage);

        Page<Product> result = productInteractor.getProducts(
                "any", "price", true, 0
        );

        assertEquals(10, result.getTotalElements());
        assertEquals(2, result.getTotalPages());
        assertEquals(5, result.getContent().size());
    }
}