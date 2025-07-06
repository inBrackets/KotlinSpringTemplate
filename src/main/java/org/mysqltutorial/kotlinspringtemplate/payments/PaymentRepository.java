package org.mysqltutorial.kotlinspringtemplate.payments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("SELECT p.paymentDate, SUM(p.amount) FROM PaymentEntity p GROUP BY p.paymentDate ORDER BY p.paymentDate ASC")
    List<PaymentAmountsByDateDto> findAllTotalPaymentsGroupedByDate();

}
