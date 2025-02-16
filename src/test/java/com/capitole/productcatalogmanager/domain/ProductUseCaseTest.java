package com.capitole.productcatalogmanager.domain;

import com.capitole.productcatalogmanager.application.usecase.ProductUseCase;
import com.capitole.productcatalogmanager.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class ProductUseCaseTest {

    private ProductUseCase productUseCase;

    @BeforeEach
    void setUp() {
        productUseCase = mock(ProductUseCase.class);
    }

    @Test
    public void whenNoResultsThenReturnEmptyList() {
        when(productUseCase.getProducts(any(), any(), eq(true))).thenReturn(getEmptyList());
        List<Product> list = productUseCase.getProducts(null, "sku", true);
        assertTrue(list.isEmpty());
    }

    @Test
    public void whenFindProductsThenReturnAllOfThem() {
        List<Product> expected = getProducts();
        when(productUseCase.getProducts(any(), any(), eq(true))).thenReturn(expected);
        List<Product> actual = productUseCase.getProducts(null, "sku", true);
        assertEquals(expected, actual);
    }

    private List<Product> getEmptyList() {
        return Collections.EMPTY_LIST;
    }

    private List<Product> getProducts() {
        return List.of(new Product("SKU001",
                BigDecimal.valueOf(100.0),
                "Product 1",
                "Description 1"));
    }
}
