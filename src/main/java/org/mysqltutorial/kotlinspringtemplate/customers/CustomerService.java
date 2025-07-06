package org.mysqltutorial.kotlinspringtemplate.customers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toCustomerDto)
                .collect(Collectors.toList());
    }

    public void printAllCustomers() {
        for(CustomerDto c : getAllCustomers()) {
            System.out.println(c.toString());
        }
    }
}
