package org.mysqltutorial.kotlinspringtemplate.orders;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class OrderControllerTestConfig {

    @Bean
    public OrderService orderService() {
        return Mockito.mock(OrderService.class);
    }
}
