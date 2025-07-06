package org.mysqltutorial.kotlinspringtemplate.productlines;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductLineService {

    private ProductLineRepository productLineRepository;
    private ProductLineMapper productLineMapper;

    public List<ProductLineDto> getAllProductLines() {
        return productLineRepository.findAll()
                .stream().map(productLineMapper::toProductLineDto)
                .toList();
    }
}
