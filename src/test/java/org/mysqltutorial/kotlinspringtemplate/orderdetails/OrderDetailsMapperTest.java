package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mysqltutorial.kotlinspringtemplate.orders.OrderEntity;
import org.mysqltutorial.kotlinspringtemplate.products.ProductEntity;

import java.math.BigDecimal;

class OrderDetailsMapperTest {

    private final OrderDetailsMapper mapper = Mappers.getMapper(OrderDetailsMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToOrderDetailsDto() {
        ProductEntity product = new ProductEntity();
        product.setProductCode("S18_1749");

        OrderEntity order = new OrderEntity();
        order.setOrderNumber(10100);

        OrderDetailsEntity orderDetails = OrderDetailsEntity.builder()
                .order(order)
                .product(product)
                .quantityOrdered(30)
                .priceEach(new BigDecimal("136.00"))
                .orderLineNumber(3)
                .build();

        OrderDetailsDto dto = mapper.toOrderDetailsDto(orderDetails);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getOrderNumber()).isEqualTo(orderDetails.getOrder().getOrderNumber());
        softly.assertThat(dto.getProductCode()).isEqualTo(orderDetails.getProduct().getProductCode());
        softly.assertThat(dto.getQuantityOrdered()).isEqualTo(orderDetails.getQuantityOrdered());
        softly.assertThat(dto.getPriceEach()).isEqualTo(orderDetails.getPriceEach());
        softly.assertThat(dto.getOrderLineNumber()).isEqualTo(orderDetails.getOrderLineNumber());

        softly.assertAll();
    }
}