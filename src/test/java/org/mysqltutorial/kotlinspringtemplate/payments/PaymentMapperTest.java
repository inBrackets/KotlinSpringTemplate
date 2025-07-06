package org.mysqltutorial.kotlinspringtemplate.payments;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mysqltutorial.kotlinspringtemplate.customers.CustomerEntity;

import java.math.BigDecimal;
import java.util.Date;

class PaymentMapperTest {

    private final PaymentMapper mapper = Mappers.getMapper(PaymentMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToPaymentDto() {

        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerNumber(123L);

        PaymentEntity payment = PaymentEntity.builder()
                .customer(customer)
                .checkNumber("HQ336336")
                .paymentDate(new Date())
                .amount(new BigDecimal("6066.78"))
                .build();

        PaymentDto dto = mapper.toPaymentDto(payment);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getCheckNumber()).isNotNull();
        softly.assertThat(dto.getPaymentDate()).isNotNull();
        softly.assertThat(dto.getAmount()).isNotNull();
        softly.assertThat(dto.getPaymentDate()).isNotNull();
        softly.assertThat(dto.getCustomerNumber()).isEqualTo(customer.getCustomerNumber());

        softly.assertAll();
    }
}