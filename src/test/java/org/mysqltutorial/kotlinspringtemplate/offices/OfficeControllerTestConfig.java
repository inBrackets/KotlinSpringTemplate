package org.mysqltutorial.kotlinspringtemplate.offices;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class OfficeControllerTestConfig {

    @Bean
    public OfficeService officeService() {
        return Mockito.mock(OfficeService.class);
    }

}
