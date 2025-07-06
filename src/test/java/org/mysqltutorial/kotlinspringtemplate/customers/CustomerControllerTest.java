package org.mysqltutorial.kotlinspringtemplate.customers;

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
 * Tests for {@link CustomerController }
 */
@WebMvcTest(CustomerController.class)
@Import(CustomerControllerTestConfig.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerService customerService;

    @Test
    void testGetAllCustomers() throws Exception {

        CustomerDto customerDto1 = CustomerDto.builder()
                .customerNumber(103)
                .customerName("Atelier graphique")
                .salesRepEmployeeNumber(1370)
                .creditLimit(new BigDecimal("21000.00"))
                .build();

        CustomerDto customerDto2 = CustomerDto.builder()
                .customerNumber(112)
                .customerName("Signal Gift Stores")
                .salesRepEmployeeNumber(1166)
                .creditLimit(new BigDecimal("71800.00"))
                .build();

        List<CustomerDto> customers = List.of(customerDto1, customerDto2);

        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].customerNumber").value(customerDto1.getCustomerNumber()))
                .andExpect(jsonPath("$[0].customerName").value(customerDto1.getCustomerName()))
                .andExpect(jsonPath("$[0].salesRepEmployeeNumber").value(customerDto1.getSalesRepEmployeeNumber()))
                .andExpect(jsonPath("$[0].creditLimit").value(customerDto1.getCreditLimit()))
                .andExpect(jsonPath("$[1].customerNumber").value(customerDto2.getCustomerNumber()))
                .andExpect(jsonPath("$[1].customerName").value(customerDto2.getCustomerName()))
                .andExpect(jsonPath("$[1].salesRepEmployeeNumber").value(customerDto2.getSalesRepEmployeeNumber()))
                .andExpect(jsonPath("$[1].creditLimit").value(customerDto2.getCreditLimit()));
    }
}