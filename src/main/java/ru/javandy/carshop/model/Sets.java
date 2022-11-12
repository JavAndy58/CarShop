package ru.javandy.carshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "sets")
@NoArgsConstructor
public class Sets {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount")
    private int amount;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id")
    private List<Detail> details;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Order> orders;
}
