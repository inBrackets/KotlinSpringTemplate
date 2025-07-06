package org.mysqltutorial.kotlinspringtemplate.productlines;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.mysqltutorial.kotlinspringtemplate.products.ProductEntity;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "productlines")
public class ProductLineEntity {

    @Id
    @GeneratedValue
    @Column(name = "product_line")
    private String productLine;

    @Column(name = "text_description")
    private String textDescription;

    @Column(name = "html_description")
    private String htmlDescription;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "productLine")
    @ToString.Exclude
    private List<ProductEntity> products;
}
