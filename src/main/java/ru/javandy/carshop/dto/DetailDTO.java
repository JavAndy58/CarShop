package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailDTO {
    private int id;
    private String name;
    private int amount;
    private double retailPrice;
    private String supplier;
    private boolean bringing;
}
