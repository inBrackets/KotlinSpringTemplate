package org.mysqltutorial.kotlinspringtemplate.payments;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class PaymentControllerTestConfig {

    @Bean
    public PaymentService paymentService() {
        return Mockito.mock(PaymentService.class);
    }
}
