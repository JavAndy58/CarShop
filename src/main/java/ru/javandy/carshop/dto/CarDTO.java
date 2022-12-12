package ru.javandy.carshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;
}
