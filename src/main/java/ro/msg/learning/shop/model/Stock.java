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
    @Column(name = "QUANTITY")
    private int quantity;
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public String toCsvFormat() {
        return this.id + "," + this.product.getName() + "," + this.quantity;
    }
}
