package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mysqltutorial.kotlinspringtemplate.orders.OrderEntity;
import org.mysqltutorial.kotlinspringtemplate.products.ProductEntity;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orderdetails")
public class OrderDetailsEntity {

    @EmbeddedId
    private OrderDetailsEntityEmbeddedId embeddedId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderNumber")
    @JoinColumn(name = "order_number")
    @ToString.Exclude
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productCode")
    @JoinColumn(name = "product_code")
    @ToString.Exclude
    private ProductEntity product;

    @Column(name = "quantity_ordered")
    private int quantityOrdered;

    @Column(name = "price_each")
    private BigDecimal priceEach;

    @Column(name = "order_line_number")
    private int orderLineNumber;
}
