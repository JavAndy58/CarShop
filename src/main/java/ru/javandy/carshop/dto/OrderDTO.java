package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;

    @JsonProperty("car")
    private CarDTO carDTO;

    @JsonProperty("detail")
    private List<DetailDTO> detailsDTO;

    @JsonProperty("customer")
    private CustomerDTO customerDTO;
}
