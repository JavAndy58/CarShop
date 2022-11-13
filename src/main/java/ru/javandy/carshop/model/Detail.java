package ru.javandy.carshop.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "details")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double purchasePrice;
    private double retailPrice;

    public Detail() {
    }

    public Detail(String name, double purchasePrice, double retailPrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
    }
}
