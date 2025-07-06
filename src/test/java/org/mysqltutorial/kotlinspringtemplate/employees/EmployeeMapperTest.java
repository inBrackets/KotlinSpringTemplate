package org.mysqltutorial.kotlinspringtemplate.employees;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mysqltutorial.kotlinspringtemplate.offices.OfficeEntity;

class EmployeeMapperTest {

    private final EmployeeMapper mapper = Mappers.getMapper(EmployeeMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToEmployeeDto() {
        OfficeEntity office = new OfficeEntity();
        office.setOfficeCode("1");

        EmployeeEntity manager = new EmployeeEntity();
        manager.setEmployeeNumber(4);

        EmployeeEntity employee = EmployeeEntity.builder()
                .employeeNumber(1002)
                .lastName("Murphy")
                .firstName("Diane")
                .extension("x5800")
                .email("dmurphy@classicmodelcars.com")
                .office(office)
                .reportsTo(manager)
                .jobTitle("President")
                .build();

        EmployeeDto dto = mapper.toEmployeeDto(employee);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getEmployeeNumber()).isEqualTo(employee.getEmployeeNumber());
        softly.assertThat(dto.getLastName()).isEqualTo(employee.getLastName());
        softly.assertThat(dto.getFirstName()).isEqualTo(employee.getFirstName());
        softly.assertThat(dto.getExtension()).isEqualTo(employee.getExtension());
        softly.assertThat(dto.getEmail()).isEqualTo(employee.getEmail());
        softly.assertThat(dto.getOfficeCode()).isEqualTo(office.getOfficeCode());
        softly.assertThat(dto.getReportsTo()).isEqualTo(manager.getEmployeeNumber());
        softly.assertThat(dto.getJobTitle()).isEqualTo(employee.getJobTitle());

        softly.assertAll();
    }
}