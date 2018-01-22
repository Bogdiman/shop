package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location shippedFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
