package org.mysqltutorial.kotlinspringtemplate.products;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface ProductMapper {

    @Mapping(source = "productLine.productLine", target = "productLine")
    ProductDto toProductDto(ProductEntity order);
}
