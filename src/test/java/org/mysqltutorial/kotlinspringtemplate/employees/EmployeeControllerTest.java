package org.mysqltutorial.kotlinspringtemplate.employees;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link EmployeeController }
 */
@WebMvcTest(EmployeeController.class)
@Import(EmployeeControllerTestConfig.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() throws Exception {

        EmployeeDto employeeDto1 = EmployeeDto.builder()
                .employeeNumber(1002)
                .lastName("Murphy")
                .firstName("Diane")
                .extension("x5800")
                .email("dmurphy@classicmodelcars.com")
                .officeCode("1")
                .reportsTo(6)
                .jobTitle("President")
                .build();

        EmployeeDto employeeDto2 = EmployeeDto.builder()
                .employeeNumber(1056)
                .lastName("Patterson")
                .firstName("Mary")
                .extension("x4611")
                .email("mpatterso@classicmodelcars.com")
                .officeCode("1")
                .reportsTo(1002)
                .jobTitle("VP Sales")
                .build();

        List<EmployeeDto> employees = List.of(employeeDto1, employeeDto2);

        when(employeeService.getAllEmployees()).thenReturn(employees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].employeeNumber").value(employeeDto1.getEmployeeNumber()))
                .andExpect(jsonPath("$[0].lastName").value(employeeDto1.getLastName()))
                .andExpect(jsonPath("$[0].officeCode").value(employeeDto1.getOfficeCode()))
                .andExpect(jsonPath("$[0].reportsTo").value(employeeDto1.getReportsTo()))
                .andExpect(jsonPath("$[1].employeeNumber").value(employeeDto2.getEmployeeNumber()))
                .andExpect(jsonPath("$[1].lastName").value(employeeDto2.getLastName()))
                .andExpect(jsonPath("$[1].officeCode").value(employeeDto2.getOfficeCode()))
                .andExpect(jsonPath("$[1].reportsTo").value(employeeDto2.getReportsTo()));
    }
}