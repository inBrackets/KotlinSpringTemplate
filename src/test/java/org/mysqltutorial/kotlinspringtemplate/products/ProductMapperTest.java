package org.mysqltutorial.kotlinspringtemplate.products;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

class ProductMapperTest {

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);
    private final SoftAssertions softly = new SoftAssertions();

    @Test
    void testToProductDto() {

        ProductEntity product = ProductEntity.builder()
                .productCode("S12_3148")
                .productName("1969 Corvair Monza")
                .productScale("1:18")
                .productVendor("Welly Diecast Productions")
                .productDescription("1:18 scale die-cast about 10\" long doors open, hood opens, trunk opens and wheels roll")
                .quantityInStock(6906)
                .buyPrice(new BigDecimal("89.14"))
                .msrp(new BigDecimal("151.08"))
                .build();

        ProductDto dto = mapper.toProductDto(product);

        softly.assertThat(dto).isNotNull();
        softly.assertThat(dto.getProductCode()).isNotNull();
        softly.assertThat(dto.getProductName()).isNotNull();
        softly.assertThat(dto.getProductScale()).isNotNull();
        softly.assertThat(dto.getProductVendor()).isNotNull();
        softly.assertThat(dto.getProductDescription()).isNotNull();
        softly.assertThat(dto.getQuantityInStock()).isNotNull();
        softly.assertThat(dto.getBuyPrice()).isNotNull();
        softly.assertThat(dto.getMsrp()).isNotNull();

        softly.assertAll();
    }
}