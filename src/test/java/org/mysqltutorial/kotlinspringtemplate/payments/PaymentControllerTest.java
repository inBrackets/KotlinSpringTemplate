package org.mysqltutorial.kotlinspringtemplate.payments;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link PaymentController }
 */
@WebMvcTest(PaymentController.class)
@Import(PaymentControllerTestConfig.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PaymentService paymentService;

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    static final SimpleDateFormat jsonSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BeforeAll
    static void beforeAll() {
        // sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        jsonSdf.setTimeZone(TimeZone.getTimeZone("UTC"));
    }


    @Test
    void testGetAllPayments() throws Exception {

        PaymentDto paymentDto1 = PaymentDto.builder()
                .customerNumber(10100)
                .checkNumber("HQ336336")
                .paymentDate(sdf.parse("2004-10-19 00:00:00.0"))
                .amount(new BigDecimal("6066.78"))
                .build();

        PaymentDto paymentDto2 = PaymentDto.builder()
                .customerNumber(10100)
                .checkNumber("JM555205")
                .paymentDate(sdf.parse("2003-06-05 00:00:00.0"))
                .amount(new BigDecimal("14571.44"))
                .build();

        List<PaymentDto> payments = List.of(paymentDto1, paymentDto2);

        when(paymentService.getAllPayments()).thenReturn(payments);

        mockMvc.perform(get("/payments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].customerNumber").value(paymentDto1.getCustomerNumber()))
                .andExpect(jsonPath("$[0].checkNumber").value(paymentDto1.getCheckNumber()))
                .andExpect(jsonPath("$[0].paymentDate").value(jsonSdf.format(paymentDto1.getPaymentDate())))
                .andExpect(jsonPath("$[0].amount").value(paymentDto1.getAmount()))
                .andExpect(jsonPath("$[1].customerNumber").value(paymentDto2.getCustomerNumber()))
                .andExpect(jsonPath("$[1].checkNumber").value(paymentDto2.getCheckNumber()))
                .andExpect(jsonPath("$[1].paymentDate").value(jsonSdf.format(paymentDto2.getPaymentDate())))
                .andExpect(jsonPath("$[1].amount").value(paymentDto2.getAmount()));
    }

    @Test
    void testGetAmountByDate() throws Exception {

        PaymentAmountsByDateDto dto1 = PaymentAmountsByDateDto.builder()
                .paymentDate(sdf.parse("2004-10-19 00:00:00.0"))
                .amount(new BigDecimal("6066.78"))
                .build();

        PaymentAmountsByDateDto dto2 = PaymentAmountsByDateDto.builder()
                .paymentDate(sdf.parse("2003-06-05 00:00:00.0"))
                .amount(new BigDecimal("14571.44"))
                .build();

        List<PaymentAmountsByDateDto> payments = List.of(dto1, dto2);

        when(paymentService.getSumOfAllPaymentsAmountsGroupedByDate()).thenReturn(payments);

        mockMvc.perform(get("/payments/AmountByDate"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].paymentDate").value(jsonSdf.format(dto1.getPaymentDate())))
                .andExpect(jsonPath("$[0].amount").value(dto1.getAmount()))
                .andExpect(jsonPath("$[1].paymentDate").value(jsonSdf.format(dto2.getPaymentDate())))
                .andExpect(jsonPath("$[1].amount").value(dto2.getAmount()));

    }
}