package com.capitole.productcatalogmanager.infrastructure;

import com.capitole.productcatalogmanager.ProductCatalogManagerApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProductCatalogManagerApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenFindProductsReturnProductDto() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content.length()").value(8));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenSortByInvalidFieldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/products?sortBy=pricee"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Property reference error"));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenSortByValidFieldReturnOk() throws Exception {
        mockMvc.perform(get("/products?sortBy=price&asc=false"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].sku").value("SKU0002"))
                .andExpect(jsonPath("$.content[0].price").value(499.00));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenFindProductsWithCategoryFilterReturnFilteredProducts() throws Exception {
        mockMvc.perform(get("/products?category=Electronics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].category").value("Electronics"))
                .andExpect(jsonPath("$.content.length()").value(8));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenFindProductsWithPaginationReturnCorrectPage() throws Exception {
        mockMvc.perform(get("/products?page=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").value(1))
                .andExpect(jsonPath("$.size").value(8))
                .andExpect(jsonPath("$.totalElements").value(30))
                .andExpect(jsonPath("$.totalPages").value(4))
                .andExpect(jsonPath("$.content.length()").value(8));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenPriceWithDiscountCalculatedCorrectly() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].priceWithDiscount").value(16.99));
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenInvalidPageNumberReturnBadRequest() throws Exception {
        mockMvc.perform(get("/products?page=-1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Invalid argument"));
    }

}

