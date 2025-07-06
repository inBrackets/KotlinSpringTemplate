package org.mysqltutorial.kotlinspringtemplate.productlines;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link ProductLineController }
 */
@WebMvcTest(ProductLineController.class)
@Import(ProductLineControllerTestConfig.class)
class ProductLineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductLineService productLineService;

    @Test
    void testGetAllProductLines() throws Exception {

        ProductLineDto productLineDto1 = ProductLineDto.builder()
                .productLine("Ships")
                .textDescription("The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.")
                .build();

        ProductLineDto productLineDto2 = ProductLineDto.builder()
                .productLine("Planes")
                .textDescription("Unique, diecast airplane and helicopter replicas suitable for collections, as well as home, office or classroom decorations. Models contain stunning details such as official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. Most come fully assembled and with a certificate of authenticity from their manufacturers.")
                .build();

        List<ProductLineDto> productLines = List.of(productLineDto1, productLineDto2);

        when(productLineService.getAllProductLines()).thenReturn(productLines);

        mockMvc.perform(get("/productLines"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].productLine").value(productLineDto1.getProductLine()))
                .andExpect(jsonPath("$[0].textDescription").value(productLineDto1.getTextDescription()))
                .andExpect(jsonPath("$[1].productLine").value(productLineDto2.getProductLine()))
                .andExpect(jsonPath("$[1].textDescription").value(productLineDto2.getTextDescription()));
    }
}