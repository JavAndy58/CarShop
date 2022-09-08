package ru.javandy.carshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "cars_id")
    private List<Car> cars;
}
