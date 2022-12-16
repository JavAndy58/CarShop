package ru.javandy.carshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

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

    @JsonProperty("car")
    private CarDTO carDTO;

    @JsonProperty("detail")
    private List<DetailDTO> detailsDTO = new ArrayList<>();

    @JsonProperty("customer")
    private CustomerDTO customerDTO;

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

    public CarDTO getCarDTO() {
        return carDTO;
    }

    public void setCarDTO(CarDTO carDTO) {
        this.carDTO = carDTO;
    }

    public List<DetailDTO> getDetailsDTO() {
        return detailsDTO;
    }

    public void setDetailsDTO(List<DetailDTO> detailsDTO) {
        this.detailsDTO = detailsDTO;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }
}
