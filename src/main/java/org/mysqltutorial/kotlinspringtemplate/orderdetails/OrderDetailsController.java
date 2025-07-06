package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @GetMapping
    public List<OrderDetailsDto> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }
}
