package ru.javandy.carshop.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private int id;
    private String name;
    private String phoneNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
