package org.mysqltutorial.kotlinspringtemplate.employees;

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
import org.mysqltutorial.kotlinspringtemplate.customers.CustomerEntity;
import org.mysqltutorial.kotlinspringtemplate.offices.OfficeEntity;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue
    @Column(name = "employee_number")
    private long employeeNumber;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "extension")
    private String extension;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "office_code")
    @ToString.Exclude
    private OfficeEntity office;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reports_to")
    @ToString.Exclude
    private EmployeeEntity reportsTo;

    @Column(name = "job_title")
    private String jobTitle;

    @OneToMany(mappedBy = "employee")
    @Builder.Default
    @ToString.Exclude
    private List<CustomerEntity> customers = new ArrayList<CustomerEntity>();

    @OneToMany(mappedBy = "reportsTo")
    @Builder.Default
    @ToString.Exclude
    private List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
}