package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface OrderDetailsMapper {

    @Mapping(source = "order.orderNumber", target = "orderNumber")
    @Mapping(source = "product.productCode", target = "productCode")
    OrderDetailsDto toOrderDetailsDto(OrderDetailsEntity orderDetails);
}
