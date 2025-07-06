package org.mysqltutorial.kotlinspringtemplate.payments;

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
class PaymentRepositoryTest {

    @Autowired
    PaymentRepository paymentRepository;

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
        softly.assertThat(paymentRepository.findAll())
                .hasSize(273);

        long queryCount = stats.getPrepareStatementCount();
        softly.assertThat(queryCount).isEqualTo(1);

        softly.assertAll();
    }

    @Test
    void findAllTotalPaymentsGroupedByDate() {
        softly.assertThat(paymentRepository.findAllTotalPaymentsGroupedByDate())
                .hasSize(232);

        long queryCount = stats.getPrepareStatementCount();
        softly.assertThat(queryCount).isEqualTo(1);

        softly.assertAll();
    }
}