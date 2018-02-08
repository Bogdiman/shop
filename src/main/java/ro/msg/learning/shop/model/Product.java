package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PRICE")
    private BigDecimal price;
    @Column(name = "WEIGHT")
    private double weight;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory productCategory;
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;
}
