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
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenSortByInvalidReturnBadRequest() throws Exception {
        mockMvc.perform(get("/products?sortBy=pricee"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = "/sql/INSERT_DATA.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/sql/CLEAN_DATA.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void whenSortByValidReturnOk() throws Exception {
        mockMvc.perform(get("/products?sortBy=price"))
                .andExpect(status().isOk());
    }

}
