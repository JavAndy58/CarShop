package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
