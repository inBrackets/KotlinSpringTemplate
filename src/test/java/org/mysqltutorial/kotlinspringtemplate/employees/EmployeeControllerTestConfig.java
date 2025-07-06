package org.mysqltutorial.kotlinspringtemplate.employees;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EmployeeControllerTestConfig {

    @Bean
    public EmployeeService employeeService() {
        return Mockito.mock(EmployeeService.class);
    }

}
