package org.mysqltutorial.kotlinspringtemplate.payments;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDto {

    private String checkNumber;
    private long customerNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date paymentDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "#0.00")
    private BigDecimal amount;
}