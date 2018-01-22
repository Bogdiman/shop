package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;
    private int quantity;
}
