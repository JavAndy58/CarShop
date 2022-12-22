package ru.javandy.carshop.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private int id;
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;
    private CarDTO car;
    private List<DetailDTO> details = new ArrayList<>();
    private CustomerDTO customer;

    private double sumMoneyDetail;
    private double totalOrder;
    private double payOrder;

    public OrderDTO() {
    }
}
