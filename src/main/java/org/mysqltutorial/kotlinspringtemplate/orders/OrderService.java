package org.mysqltutorial.kotlinspringtemplate.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderMapper::toOrderDto)
                .toList();
    }

    public Optional<OrderDto> getOrderById(long id) {
        return orderRepository.findById(id).map(orderMapper::toOrderDto);
    }

    public void printAllOrdersByCustomer(long customerId) {
        for (OrderEntity order : orderRepository.findByCustomerNumber(customerId)) {
            System.out.println(order);
        }
    }
}
