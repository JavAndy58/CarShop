package ru.javandy.carshop.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public OrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public double getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(double prepayment) {
        this.prepayment = prepayment;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(boolean cardPayment) {
        this.cardPayment = cardPayment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public List<DetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DetailDTO> details) {
        this.details = details;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }
}
