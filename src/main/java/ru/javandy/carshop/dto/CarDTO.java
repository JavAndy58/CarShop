package ru.javandy.carshop.dto;

import lombok.Data;

@Data
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;

    public CarDTO() {
    }

    public CarDTO(String name) {
        this.name = name;
    }

    public CarDTO(String name, String vinCode) {
        this.name = name;
        this.vinCode = vinCode;
    }
}
