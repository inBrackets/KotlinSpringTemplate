package org.mysqltutorial.kotlinspringtemplate.offices;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

    @Override
    @EntityGraph(attributePaths = "employees")
    List<OfficeEntity> findAll();
}
