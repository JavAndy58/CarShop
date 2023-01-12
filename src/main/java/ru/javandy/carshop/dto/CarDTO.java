package ru.javandy.carshop.dto;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;

    public CarDTO(String name, String vinCode) {
        this.name = name;
        this.vinCode = vinCode;
    }
}
