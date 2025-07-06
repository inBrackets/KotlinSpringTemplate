package org.mysqltutorial.kotlinspringtemplate.customers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface CustomerMapper {

    @Mapping(source = "employee.employeeNumber", target = "salesRepEmployeeNumber")
    CustomerDto toCustomerDto(CustomerEntity customer);
}
