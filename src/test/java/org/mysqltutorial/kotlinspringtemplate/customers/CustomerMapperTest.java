package org.mysqltutorial.kotlinspringtemplate.customers;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mysqltutorial.kotlinspringtemplate.employees.EmployeeEntity;

import java.math.BigDecimal;

class CustomerMapperTest {

    private final CustomerMapper mapper = Mappers.getMapper(CustomerMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToCustomerDto() {
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeNumber(1370);

        CustomerEntity customer = CustomerEntity.builder()
                .customerNumber(103)
                .customerName("Atelier graphique")
                .contactLastName("Schmitt")
                .contactFirstName("Carine")
                .phone("40.32.2555")
                .addressLine1("54, rue Royale")
                .city("Nantes")
                .postalCode("44000")
                .country("France")
                .employee(employee)
                .creditLimit(new BigDecimal("21000.00"))
                .build();

        CustomerDto dto = mapper.toCustomerDto(customer);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getCustomerName()).isNotNull();
        softly.assertThat(dto.getContactFirstName()).isNotNull();
        softly.assertThat(dto.getContactLastName()).isNotNull();
        softly.assertThat(dto.getPhone()).isNotNull();
        softly.assertThat(dto.getAddressLine1()).isNotNull();
        softly.assertThat(dto.getCity()).isNotNull();
        softly.assertThat(dto.getSalesRepEmployeeNumber()).isEqualTo(employee.getEmployeeNumber());

        softly.assertAll();
    }
}