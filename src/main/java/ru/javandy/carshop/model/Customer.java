package ru.javandy.carshop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "customers")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    @JoinColumn(name = "customer_id")
    private List<Car> cars = new ArrayList<>();

    public Customer(String name, String phoneNumber, List<Car> cars) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.cars = cars;
    }
}
