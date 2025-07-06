package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, Long> {

    @EntityGraph(attributePaths = {"product", "order"})  // prevent the n+1 problem
    List<OrderDetailsEntity> findAll();

    @Query("""
                    SELECT p.productName
                    FROM OrderDetailsEntity od
                    JOIN od.product p
                    JOIN od.order o
                    WHERE o.orderNumber = :orderNumber
            """)
    List<String> findProductNamesByOrderNumber(@Param("orderNumber") long orderNumber);
}
