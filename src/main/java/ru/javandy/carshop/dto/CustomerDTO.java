package ru.javandy.carshop.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;
    private List<CarDTO> cars = new ArrayList<>();

    public CustomerDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public void addCarDTO(CarDTO carDTO) {
        this.cars.add(carDTO);
    }

    public void removeCarDTO(CarDTO carDTO) {
        this.cars.remove(carDTO);
    }
}


