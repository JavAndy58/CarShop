package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private int id;
    private String name;
    private String vinCode;

    @JsonBackReference
    @JsonProperty("customer")
    private CustomerDTO customerDTO;

}
