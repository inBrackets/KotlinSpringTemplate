package org.mysqltutorial.kotlinspringtemplate.offices;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Tests for {@link OfficeController }
 */
@WebMvcTest(OfficeController.class)
@Import(OfficeControllerTestConfig.class)
class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OfficeService officeService;

    @Test
    void testGetAllOffices() throws Exception {

        OfficeDto dto1 = OfficeDto.builder()
                .officeCode("1")
                .city("San Francisco")
                .phone("+1 650 219 4782")
                .addressLine1("100 Market Street")
                .addressLine2("Suite 300")
                .state("CA")
                .country("USA")
                .postalCode("94080")
                .territory("NA")
                .build();

        OfficeDto dto2 = OfficeDto.builder()
                .officeCode("2")
                .city("Boston")
                .phone("+1 215 837 0825")
                .addressLine1("1550 Court Place")
                .addressLine2("Suite 102")
                .state("MA")
                .country("USA")
                .postalCode("02107")
                .territory("NA")
                .build();

        List<OfficeDto> offices = List.of(dto1, dto2);

        when(officeService.getAllOffices()).thenReturn(offices);

        mockMvc.perform(get("/offices"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].officeCode").value(dto1.getOfficeCode()))
                .andExpect(jsonPath("$[0].city").value(dto1.getCity()))
                .andExpect(jsonPath("$[0].phone").value(dto1.getPhone()))
                .andExpect(jsonPath("$[0].addressLine1").value(dto1.getAddressLine1()))
                .andExpect(jsonPath("$[1].officeCode").value(dto2.getOfficeCode()))
                .andExpect(jsonPath("$[1].city").value(dto2.getCity()))
                .andExpect(jsonPath("$[1].phone").value(dto2.getPhone()))
                .andExpect(jsonPath("$[1].addressLine1").value(dto2.getAddressLine1()));
    }
}