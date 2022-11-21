package ru.javandy.carshop.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "cars")
public class Sets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private List<Detail> details;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Order> orders;

    public Sets() {
    }

    public Sets(int amount, List<Detail> details, List<Order> orders) {
        this.amount = amount;
        this.details = details;
        this.orders = orders;
    }
}
