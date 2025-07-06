package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class OrderDetailsControllerTestConfig {

    @Bean
    public OrderDetailsService orderDetailsService() {
        return Mockito.mock(OrderDetailsService.class);
    }

}
