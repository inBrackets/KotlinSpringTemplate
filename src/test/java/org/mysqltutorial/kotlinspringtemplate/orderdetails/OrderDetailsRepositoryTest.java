package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import jakarta.persistence.EntityManagerFactory;
import org.assertj.core.api.SoftAssertions;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderDetailsRepositoryTest {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

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

    @ParameterizedTest
    @MethodSource("provideArguments")
    void findProductNamesByOrderNumber(long orderNumber, int expectedCountOfProducts) {
        long count = orderDetailsRepository.findProductNamesByOrderNumber(orderNumber).size();
        assertEquals(expectedCountOfProducts, count);
    }

    @Test
    void testFindAll() {
        softly.assertThat(orderDetailsRepository.findAll())
                .hasSize(2996);

        long queryCount = stats.getPrepareStatementCount();
        softly.assertThat(queryCount).isEqualTo(1);

        softly.assertAll();
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of(10100, 4),
                Arguments.of(10101, 4),
                Arguments.of(10102, 2),
                Arguments.of(10103, 16),
                Arguments.of(10104, 13),
                Arguments.of(10105, 15)
        );
    }
}