package ru.javandy.carshop.dto;

import lombok.Data;
import ru.javandy.carshop.model.Customer;

@Data
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;
    private Customer customer;
}
