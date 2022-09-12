package ru.javandy.carshop.dto;

import lombok.Data;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {
    int id;
    Date created;
    Customer customer;
    List<Detail> details;
    double sumDetail;
    double prepayment;
    double sumOrder;
    boolean delivered;
}
