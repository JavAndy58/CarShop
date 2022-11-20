package ru.javandy.carshop.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String vinCode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Car() {
    }

    public Car(String name, String vinCode, Customer customer) {
        this.name = name;
        this.vinCode = vinCode;
        this.customer = customer;
    }
}
