package org.mysqltutorial.kotlinspringtemplate.offices;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class OfficeMapperTest {

    private final OfficeMapper mapper = Mappers.getMapper(OfficeMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToOfficeDto() {

        OfficeEntity office = OfficeEntity.builder()
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

        OfficeDto dto = mapper.toOfficeDto(office);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getOfficeCode()).isEqualTo(office.getOfficeCode());
        softly.assertThat(dto.getCity()).isEqualTo(office.getCity());
        softly.assertThat(dto.getPhone()).isEqualTo(office.getPhone());
        softly.assertThat(dto.getAddressLine1()).isEqualTo(office.getAddressLine1());
        softly.assertThat(dto.getAddressLine2()).isEqualTo(office.getAddressLine2());
        softly.assertThat(dto.getState()).isEqualTo(office.getState());
        softly.assertThat(dto.getCountry()).isEqualTo(office.getCountry());
        softly.assertThat(dto.getTerritory()).isEqualTo(office.getTerritory());

        softly.assertAll();
    }
}