package org.mysqltutorial.kotlinspringtemplate.productlines;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface ProductLineMapper {

    ProductLineDto toProductLineDto(ProductLineEntity order);
}
