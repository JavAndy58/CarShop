package ru.javandy.carshop.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class CarDto {
    private int id;
    private String name;
    private String vinCode;

    public CarDto(String name, String vinCode) {
        this.name = name;
        this.vinCode = vinCode;
    }
}
