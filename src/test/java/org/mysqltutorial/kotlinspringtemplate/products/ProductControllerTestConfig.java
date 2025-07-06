package org.mysqltutorial.kotlinspringtemplate.products;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class ProductControllerTestConfig {

    @Bean
    public ProductService productService() {
        return Mockito.mock(ProductService.class);
    }
}
