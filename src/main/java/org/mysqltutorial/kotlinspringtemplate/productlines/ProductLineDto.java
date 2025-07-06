package org.mysqltutorial.kotlinspringtemplate.productlines;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductLineDto {

    private String productLine;
    private String textDescription;
    private String htmlDescription;
}