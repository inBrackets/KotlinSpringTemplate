package org.mysqltutorial.kotlinspringtemplate.orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link OrderController }
 */
@WebMvcTest(OrderController.class)
@Import(OrderControllerTestConfig.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;

    @Test
    void testGetAllOrders() throws Exception {

        OrderDto orderDto1 = OrderDto.builder()
                .orderNumber(1)
                .status("SHIPPED")
                .comments("First order")
                .build();

        OrderDto orderDto2 = OrderDto.builder()
                .orderNumber(2)
                .status("PROCESSING")
                .comments("Second order")
                .build();

        List<OrderDto> orders = List.of(orderDto1, orderDto2);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].orderNumber").value(1))
                .andExpect(jsonPath("$[0].status").value("SHIPPED"))
                .andExpect(jsonPath("$[1].orderNumber").value(2))
                .andExpect(jsonPath("$[1].status").value("PROCESSING"));
    }

    @Test
    void testGetOrderById_shouldReturnOrderWhenFound() throws Exception {
        OrderDto orderDto = OrderDto.builder()
                .orderNumber(123)
                .orderDate(new Date())
                .status("SHIPPED")
                .comments("Fast delivery")
                .build();

        when(orderService.getOrderById(123L)).thenReturn(Optional.of(orderDto));

        mockMvc.perform(get("/orders/id/123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderNumber").value(orderDto.getOrderNumber()))
                .andExpect(jsonPath("$.status").value(orderDto.getStatus()))
                .andExpect(jsonPath("$.comments").value(orderDto.getComments()));
    }

    @Test
    void testGetOrderById_shouldReturn404WhenNotFound() throws Exception {
        when(orderService.getOrderById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/orders/id/999"))
                .andExpect(status().isNotFound());
    }
}