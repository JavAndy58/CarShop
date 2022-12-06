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
    private int amount;
    private double purchasePrice;
    private double retailPrice;
    private String supplier;
    private boolean bringing;

    public Detail() {
    }

    public Detail(String name, int amount, double purchasePrice, double retailPrice, String supplier, boolean bringing) {
        this.name = name;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.supplier = supplier;
        this.bringing = bringing;
    }
}
