package org.mysqltutorial.kotlinspringtemplate.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link ProductController }
 */
@WebMvcTest(ProductController.class)
@Import(ProductControllerTestConfig.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    void testGetAllProducts() throws Exception {

        ProductDto productDto1 = ProductDto.builder()
                .productCode("S12_3148")
                .productName("1969 Corvair Monza")
                .productScale("1:18")
                .productVendor("Welly Diecast Productions")
                .productDescription("1:18 scale die-cast about 10\" long doors open, hood opens, trunk opens and wheels roll")
                .quantityInStock(6906)
                .buyPrice(new BigDecimal("89.14"))
                .msrp(new BigDecimal("151.08"))
                .build();

        ProductDto productDto2 = ProductDto.builder()
                .productCode("S18_4027")
                .productName("1970 Triumph Spitfire")
                .productScale("1:18")
                .productVendor("Min Lin Diecast")
                .productDescription("Features include opening and closing doors. Color: White.")
                .quantityInStock(5545)
                .buyPrice(new BigDecimal("91.92"))
                .msrp(new BigDecimal("143.62"))
                .build();

        List<ProductDto> products = List.of(productDto1, productDto2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].productCode").value(productDto1.getProductCode()))
                .andExpect(jsonPath("$[0].productName").value(productDto1.getProductName()))
                .andExpect(jsonPath("$[0].productScale").value(productDto1.getProductScale()))
                .andExpect(jsonPath("$[0].productVendor").value(productDto1.getProductVendor()))
                .andExpect(jsonPath("$[0].productDescription").value(productDto1.getProductDescription()))
                .andExpect(jsonPath("$[0].quantityInStock").value(productDto1.getQuantityInStock()))
                .andExpect(jsonPath("$[0].buyPrice").value(productDto1.getBuyPrice()))
                .andExpect(jsonPath("$[0].msrp").value(productDto1.getMsrp()))
                .andExpect(jsonPath("$[1].productCode").value(productDto2.getProductCode()))
                .andExpect(jsonPath("$[1].productName").value(productDto2.getProductName()))
                .andExpect(jsonPath("$[1].productScale").value(productDto2.getProductScale()))
                .andExpect(jsonPath("$[1].productVendor").value(productDto2.getProductVendor()))
                .andExpect(jsonPath("$[1].productDescription").value(productDto2.getProductDescription()))
                .andExpect(jsonPath("$[1].quantityInStock").value(productDto2.getQuantityInStock()))
                .andExpect(jsonPath("$[1].buyPrice").value(productDto2.getBuyPrice()))
                .andExpect(jsonPath("$[1].msrp").value(productDto2.getMsrp()));
    }
}