package org.mysqltutorial.kotlinspringtemplate.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}
