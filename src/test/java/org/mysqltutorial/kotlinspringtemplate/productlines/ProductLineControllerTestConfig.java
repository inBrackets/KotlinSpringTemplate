package org.mysqltutorial.kotlinspringtemplate.productlines;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductLineControllerTestConfig {

    @Bean
    public ProductLineService productLineService() {
        return Mockito.mock(ProductLineService.class);
    }
}
