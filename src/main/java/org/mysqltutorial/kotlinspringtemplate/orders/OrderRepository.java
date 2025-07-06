package org.mysqltutorial.kotlinspringtemplate.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("""
                    SELECT o
                    FROM OrderEntity o
                    WHERE o.customer.customerNumber = :customerNumber
            """)
    List<OrderEntity> findByCustomerNumber(long customerNumber);
}
