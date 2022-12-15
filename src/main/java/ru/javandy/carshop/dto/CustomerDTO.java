package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;

//    @JsonProperty("car")
//    @JsonManagedReference
    private List<CarDTO> cars;

    public CustomerDTO() {
    }
}


