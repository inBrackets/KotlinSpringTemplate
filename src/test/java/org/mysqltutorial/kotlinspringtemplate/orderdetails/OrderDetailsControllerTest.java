package org.mysqltutorial.kotlinspringtemplate.orderdetails;

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
 * Tests for {@link OrderDetailsController }
 */
@WebMvcTest(OrderDetailsController.class)
@Import(OrderDetailsControllerTestConfig.class)
class OrderDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderDetailsService orderDetailsService;

    @Test
    void testGetAllOrderDetails() throws Exception {

        OrderDetailsDto dto1 = OrderDetailsDto.builder()
                .productCode("S18_1749")
                .quantityOrdered(30)
                .priceEach(new BigDecimal("136.00"))
                .orderLineNumber(3)
                .build();

        OrderDetailsDto dto2 = OrderDetailsDto.builder()
                .orderNumber(10100)
                .productCode("S18_2248")
                .quantityOrdered(50)
                .priceEach(new BigDecimal("55.09"))
                .orderLineNumber(2)
                .build();

        List<OrderDetailsDto> orderDetails = List.of(dto1, dto2);

        when(orderDetailsService.getAllOrderDetails()).thenReturn(orderDetails);

        mockMvc.perform(get("/orderDetails"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].orderNumber").value(dto1.getOrderNumber()))
                .andExpect(jsonPath("$[0].productCode").value(dto1.getProductCode()))
                .andExpect(jsonPath("$[0].quantityOrdered").value(dto1.getQuantityOrdered()))
                .andExpect(jsonPath("$[0].priceEach").value(dto1.getPriceEach()))
                .andExpect(jsonPath("$[1].orderNumber").value(dto2.getOrderNumber()))
                .andExpect(jsonPath("$[1].productCode").value(dto2.getProductCode()))
                .andExpect(jsonPath("$[1].quantityOrdered").value(dto2.getQuantityOrdered()))
                .andExpect(jsonPath("$[1].priceEach").value(dto2.getPriceEach()));
    }
}