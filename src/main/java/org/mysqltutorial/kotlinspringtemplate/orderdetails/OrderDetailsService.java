package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepository;
    private OrderDetailsMapper orderDetailsMapper;

    public List<OrderDetailsDto> getAllOrderDetails() {
        return orderDetailsRepository.findAll().stream()
                .map(orderDetailsMapper::toOrderDetailsDto)
                .collect(Collectors.toList());
    }

    public void printAllOrderDetails() {
        for (OrderDetailsDto orderDetailsDto : getAllOrderDetails()) {
            System.out.println(orderDetailsDto.toString());
        }
    }

    public void printAllProductNamesByOrderId(long orderId) {
        for (String productName : orderDetailsRepository.findProductNamesByOrderNumber(orderId)) {
            System.out.println(productName);
        }
    }
}
