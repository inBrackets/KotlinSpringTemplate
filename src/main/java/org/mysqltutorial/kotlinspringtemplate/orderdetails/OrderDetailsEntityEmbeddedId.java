package org.mysqltutorial.kotlinspringtemplate.orderdetails;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
class OrderDetailsEntityEmbeddedId implements Serializable {

    @Column(name = "order_number")
    private long orderNumber;

    @Column(name = "product_code")
    private String productCode;
}
