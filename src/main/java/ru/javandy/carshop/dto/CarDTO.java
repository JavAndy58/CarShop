package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.javandy.carshop.model.Customer;

@Getter
@Setter
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;

//    @JsonBackReference
//    @JsonProperty("customer")
    private CustomerDTO customer;

    public CarDTO() {
    }
}
