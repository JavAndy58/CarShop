package ru.javandy.carshop.model;

import lombok.Builder;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "customers")

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
