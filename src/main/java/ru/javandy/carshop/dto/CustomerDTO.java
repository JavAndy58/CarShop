package ru.javandy.carshop.dto;

import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;

    private List<CarDTO> cars;

    public CustomerDTO() {
    }

    public void addCarDTO(CarDTO carDTO) {
        this.cars.add(carDTO);
    }

    public void removeCarDTO(CarDTO carDTO) {
        this.cars.remove(carDTO);
    }
}


