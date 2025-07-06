package org.mysqltutorial.kotlinspringtemplate.productlines;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductLineRepository extends JpaRepository<ProductLineEntity, Long> {

    @EntityGraph(attributePaths = "products") // prevent the n+1 problem
    List<ProductLineEntity> findAll();
}
