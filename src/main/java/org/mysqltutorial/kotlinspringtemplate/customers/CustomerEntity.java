package org.mysqltutorial.kotlinspringtemplate.customers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mysqltutorial.kotlinspringtemplate.employees.EmployeeEntity;
import org.mysqltutorial.kotlinspringtemplate.orders.OrderEntity;
import org.mysqltutorial.kotlinspringtemplate.payments.PaymentEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    @Column(name = "customer_number")
    private long customerNumber;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "contact_last_name")
    private String contactLastName;

    @Column(name = "contact_first_name")
    private String contactFirstName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_rep_employee_number")
    @ToString.Exclude
    private EmployeeEntity employee;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @OneToMany(mappedBy = "customer")
    @Builder.Default
    @ToString.Exclude
    private List<OrderEntity> orders = new ArrayList<OrderEntity>();

    @OneToMany(mappedBy = "customer")
    @Builder.Default
    @ToString.Exclude
    private List<PaymentEntity> payments = new ArrayList<PaymentEntity>();

}
