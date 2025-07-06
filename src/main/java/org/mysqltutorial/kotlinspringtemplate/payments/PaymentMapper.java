package org.mysqltutorial.kotlinspringtemplate.payments;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface PaymentMapper {

    @Mapping(source = "customer.customerNumber", target = "customerNumber")
    PaymentDto toPaymentDto(PaymentEntity order);
}
