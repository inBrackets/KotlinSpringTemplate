package org.mysqltutorial.kotlinspringtemplate.products;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @EntityGraph(attributePaths = "productLine") // prevent the n+1 problem
    List<ProductEntity> findAll();
}
