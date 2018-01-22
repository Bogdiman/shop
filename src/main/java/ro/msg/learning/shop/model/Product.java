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
    private String name;
    private String description;
    private BigDecimal price;
    private double weight;
    @ManyToOne(cascade = CascadeType.ALL)
    private ProductCategory category;
    @ManyToOne(cascade = CascadeType.ALL)
    private Supplier supplier;
}
