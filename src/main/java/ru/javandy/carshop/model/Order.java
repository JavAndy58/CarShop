package ru.javandy.carshop.model;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    private double prepayment;
    private boolean delivered;
    private boolean cardPayment;
    private String note;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Detail> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(Date created, double prepayment, boolean delivered, boolean cardPayment, String note, Car car, List<Detail> details, Customer customer) {
        this.created = created;
        this.prepayment = prepayment;
        this.delivered = delivered;
        this.cardPayment = cardPayment;
        this.note = note;
        this.car = car;
        this.details = details;
        this.customer = customer;
    }
}
