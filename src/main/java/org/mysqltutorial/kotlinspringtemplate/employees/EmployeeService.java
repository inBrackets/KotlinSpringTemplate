package org.mysqltutorial.kotlinspringtemplate.employees;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toEmployeeDto)
                .collect(Collectors.toList());
    }

    public void printAllEmployees() {
        for (EmployeeDto c : getAllEmployees()) {
            System.out.println(c.toString());
        }
    }
}
