package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;

    @JsonProperty("car")
    private CarDTO carDTO;
}


