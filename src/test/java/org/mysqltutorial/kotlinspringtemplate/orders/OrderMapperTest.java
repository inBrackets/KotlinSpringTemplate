package org.mysqltutorial.kotlinspringtemplate.orders;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mysqltutorial.kotlinspringtemplate.customers.CustomerEntity;

import java.util.Date;

class OrderMapperTest {

    private final OrderMapper mapper = Mappers.getMapper(OrderMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToOrderDto() {

        CustomerEntity customer = new CustomerEntity();
        customer.setCustomerNumber(123L);

        OrderEntity order = OrderEntity.builder()
                .orderNumber(1001)
                .orderDate(new Date())
                .requiredDate(new Date())
                .shippedDate(new Date())
                .status("Shipped")
                .comments("Deliver before noon")
                .customer(customer)
                .build();

        OrderDto dto = mapper.toOrderDto(order);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getOrderNumber()).isNotNull();
        softly.assertThat(dto.getStatus()).isNotNull();
        softly.assertThat(dto.getComments()).isNotNull();
        softly.assertThat(dto.getOrderDate()).isNotNull();
        softly.assertThat(dto.getRequiredDate()).isNotNull();
        softly.assertThat(dto.getShippedDate()).isNotNull();
        softly.assertThat(dto.getCustomerNumber()).isEqualTo(customer.getCustomerNumber());

        softly.assertAll();
    }
}