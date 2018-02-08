package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ORDERR")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location shippedFrom;
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
