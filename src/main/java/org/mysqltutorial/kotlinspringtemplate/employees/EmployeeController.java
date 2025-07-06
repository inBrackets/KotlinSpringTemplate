package org.mysqltutorial.kotlinspringtemplate.employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200") // TODO: make a config class that would open exclude this endpoint from the CORS
    public List<EmployeeDto> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
