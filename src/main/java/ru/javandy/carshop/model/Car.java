package ru.javandy.carshop.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String vinCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id")
    private Customer customer;

    public Car(String name, String vinCode) {
        this.name = name;
        this.vinCode = vinCode;
    }
}
