package org.mysqltutorial.kotlinspringtemplate.offices;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/offices")
public class OfficeController {

    private final OfficeService officeService;

    @GetMapping
    public List<OfficeDto> getAllOffices() {
        return officeService.getAllOffices();
    }
}
