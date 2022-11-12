package ru.javandy.carshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "details")
@NoArgsConstructor
public class Detail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "purchase_price")
    private double purchasePrice;

    @Column(name = "retail_price")
    private double retailPrice;
}
