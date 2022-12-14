package ru.javandy.carshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonBackReference
    @JoinColumn(name="customer_id")
    @ManyToOne
    private Customer customer;

    public Car(String name, String vinCode) {
        this.name = name;
        this.vinCode = vinCode;
    }
}
