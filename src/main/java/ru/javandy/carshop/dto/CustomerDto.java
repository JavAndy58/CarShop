package ru.javandy.carshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerDto {
    private int id;
    private String name;
    private String phoneNumber;
    private List<CarDto> cars = new ArrayList<>();

    public CustomerDto(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addCarDTO(CarDto carDTO) {
        this.cars.add(carDTO);
    }

    public void removeCarDTO(CarDto carDTO) {
        this.cars.remove(carDTO);
    }
}


