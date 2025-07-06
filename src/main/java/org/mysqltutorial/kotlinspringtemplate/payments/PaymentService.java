package org.mysqltutorial.kotlinspringtemplate.payments;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepository;
    private PaymentMapper paymentMapper;

    public List<PaymentDto> getAllPayments() {
        return paymentRepository.findAll()
                .stream().map(paymentMapper::toPaymentDto)
                .toList();
    }

    public List<PaymentAmountsByDateDto> getSumOfAllPaymentsAmountsGroupedByDate() {
        return paymentRepository.findAllTotalPaymentsGroupedByDate();
    }
}
