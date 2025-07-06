package org.mysqltutorial.kotlinspringtemplate.employees;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface EmployeeMapper {

    @Mapping(source = "office.officeCode", target = "officeCode")
    @Mapping(source = "reportsTo.employeeNumber", target = "reportsTo")
    EmployeeDto toEmployeeDto(EmployeeEntity employee);
}
