package ru.javandy.carshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date created;

    @ManyToOne
    private Customer customer;

    @OneToOne
    private Detail detail;

    private double sumDetail;
    private double предоплата;
    private double итогозаказ;
    private boolean исполнено;
}
