package org.mysqltutorial.kotlinspringtemplate.customers;

import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    private Statistics stats;
    private SoftAssertions softly;

    @BeforeEach
    public void beforeEach() {
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);
        stats = sessionFactory.getStatistics();
        stats.clear();
        softly = new SoftAssertions();
    }

    @Test
    void testFindAll() {
        softly.assertThat(customerRepository.findAll())
                .hasSize(122);

        long queryCount = stats.getPrepareStatementCount();
        softly.assertThat(queryCount).isEqualTo(1);

        softly.assertAll();
    }
}