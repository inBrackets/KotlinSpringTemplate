package org.mysqltutorial.kotlinspringtemplate.orders;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface OrderMapper {

    @Mapping(source = "customer.customerNumber", target = "customerNumber")
    OrderDto toOrderDto(OrderEntity order);
}
