package ru.javandy.carshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@Table(name = "details")
@AllArgsConstructor
@NoArgsConstructor
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

    public Detail(String name, int amount, double purchasePrice, double retailPrice, String supplier, boolean bringing) {
        this.name = name;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.supplier = supplier;
        this.bringing = bringing;
    }
}
