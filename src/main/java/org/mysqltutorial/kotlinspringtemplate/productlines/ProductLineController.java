package org.mysqltutorial.kotlinspringtemplate.productlines;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/productLines")
public class ProductLineController {

    private final ProductLineService productLineService;

    @GetMapping
    public List<ProductLineDto> getAllProductLines() {
        return productLineService.getAllProductLines();
    }
}
