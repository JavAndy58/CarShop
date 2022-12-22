package ru.javandy.carshop.dto;

import lombok.Data;

@Data
public class DetailDTO {
    private int id;
    private String name;
    private int amount;
    private double purchasePrice;
    private double retailPrice;
    private String supplier;
    private boolean bringing;

    public DetailDTO() {
    }
}
