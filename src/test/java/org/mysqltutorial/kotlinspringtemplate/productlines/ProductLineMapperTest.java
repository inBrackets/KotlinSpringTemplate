package org.mysqltutorial.kotlinspringtemplate.productlines;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class ProductLineMapperTest {

    private final ProductLineMapper mapper = Mappers.getMapper(ProductLineMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToProductLineDto() {

        ProductLineEntity productLine = ProductLineEntity.builder()
                .productLine("Ships")
                .textDescription("The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.")
                .build();

        ProductLineDto dto = mapper.toProductLineDto(productLine);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getProductLine()).isNotNull();
        softly.assertThat(dto.getTextDescription()).isNotNull();

        softly.assertAll();
    }
}