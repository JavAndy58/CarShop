package ru.javandy.carshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class DetailDTO {
    private int id;
    private String name;
    private int amount;
    private double purchasePrice;
    private double retailPrice;
    private String supplier;
    private boolean bringing;
    private double sumMoney;

    public DetailDTO(String name, int amount, double purchasePrice, double retailPrice, String supplier,
                     boolean bringing, double sumMoney) {
        this.name = name;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.retailPrice = retailPrice;
        this.supplier = supplier;
        this.bringing = bringing;
        this.sumMoney = sumMoney;
    }
}
