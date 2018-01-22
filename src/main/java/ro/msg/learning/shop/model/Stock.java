package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Location location;
}
