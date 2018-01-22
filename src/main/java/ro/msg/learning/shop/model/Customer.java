package ro.msg.learning.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "USERNAME")
    private String username;
}
