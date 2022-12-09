package ru.javandy.carshop.dto;

import ru.javandy.carshop.model.Car;
import ru.javandy.carshop.model.Customer;
import ru.javandy.carshop.model.Detail;
import java.util.Date;
import java.util.List;

public class OrderDTO {
    private int id;
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;
    private Car car;
    private List<Detail> details;
    private Customer customer;

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
